package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.config.LocalDateAdapter;
import softuni.exam.models.Car;
import softuni.exam.models.Offer;
import softuni.exam.models.imports.OfferRootDto;
import softuni.exam.models.imports.OfferSeedDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;
    private final ValidationUtil validationUtil;
    private final LocalDateAdapter localDateAdapter;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper
            , XmlParser xmlParser, CarRepository carRepository, SellerRepository sellerRepository, ValidationUtil validationUtil, LocalDateAdapter localDateAdapter) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.validationUtil = validationUtil;
        this.localDateAdapter = localDateAdapter;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException, JAXBException {
        String path = "src/main/resources/files/xml/offers.xml";
        OfferRootDto offerRootDto = xmlParser.parseXml(OfferRootDto.class, path);
        StringBuilder sb = new StringBuilder();
        for (OfferSeedDto offerSeedDto : offerRootDto.getOffers()) {
            Offer offer = modelMapper.map(offerSeedDto, Offer.class);
            Car car = carRepository.findById(offerSeedDto.getCar().getId()).get();
            offer.setCar(car);
            offer.setSeller(sellerRepository.findById(offerSeedDto.getSeller().getId()).get());
            offer.setPictures(new HashSet<>(car.getPictures()));
            LocalDateTime current = offerSeedDto.getAddedOn();
            offer.setAddedOn(current);
            if (validationUtil.isValid(offer)) {
                offerRepository.saveAndFlush(offer);
                sb.append(String.format("Successfully import offer %s - %s%n",
                        offer.getAddedOn().toString(), offer.isHasGoldStatus()));

            } else {
                sb.append("Invalid offer").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        return null;
    }
}
