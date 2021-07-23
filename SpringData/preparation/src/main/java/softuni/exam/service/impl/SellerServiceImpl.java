package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Seller;
import softuni.exam.models.imports.SellerRootDto;
import softuni.exam.models.imports.SellerSeedDto;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        String path = "src/main/resources/files/xml/sellers.xml";
        SellerRootDto sellers = new SellerRootDto();
       sellers= xmlParser.parseXml(SellerRootDto.class, path);
        for (SellerSeedDto sellerSeedDto : sellers.getSellers()) {
            Seller seller = modelMapper.map(sellerSeedDto, Seller.class);
            if (validationUtil.isValid(seller) && seller.getEmail().contains("@") && seller.getEmail().contains(".")) {
                sb.append(String.format("Successfully import seller %s - %s%n",
                        seller.getLastName(), seller.getEmail()));
                sellerRepository.saveAndFlush(seller);
            } else {
                sb.append("Invalid seller").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        return null;
    }
}
