package intospring.demo.dao.impl;

import intospring.demo.dao.AccountRepository;
import intospring.demo.entity.Account;
import intospring.demo.entity.User;
import intospring.demo.exception.InvalidAccountOperation;
import intospring.demo.exception.NonExistingEntityException;
import intospring.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepo;

    @Autowired
    public void setAccountRepo(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Account createUserAccount(User user, Account account) {
        account.setId(null);
        account.setUser(user);
        user.getAccounts().add(account);
        return accountRepo.save(account);
    }

    @Override
    public void depositMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() ->
                new NonExistingEntityException(
                        String.format("Entity with ID:%s does not exist.", accountId)
                ));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InvalidAccountOperation(
                    String.format("Account ID:%s does not have enough money",
                            accountId));
        }
        account.setBalance(account.getBalance().subtract(amount));
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() ->
                new NonExistingEntityException(
                        String.format("Entity with ID:%s does not exist.", accountId)
                ));

        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void transferMoney(BigDecimal amount, Long fromId, Long toId) {
        depositMoney(amount, toId);
        withdrawMoney(amount, fromId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}
