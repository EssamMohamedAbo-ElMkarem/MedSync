package com.medsync.billingservice.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medsync.billingservice.models.BillingAccount;
import com.medsync.billingservice.models.BillingStatus;
import com.medsync.billingservice.repositories.BillingAccountRepository;

@Service
public class BillingAccountService {

    @Autowired
    private BillingAccountRepository billingAccountRepository;

    public BillingAccount createBillingAccount(BillingAccount billingAccount){
        billingAccount.setStatus(BillingStatus.ACTIVE);
        return billingAccountRepository.save(billingAccount);
    }

    public Optional<BillingAccount> getBillingAccount(UUID id){
        return billingAccountRepository.findById(id);
    }

    public List<BillingAccount> getAllBillingAccounts(){
        return billingAccountRepository.findAll();
    }
    
    public Optional<BillingAccount> updateBillingAccount(UUID id, BillingAccount updateAccount){
        return billingAccountRepository.findById(id).map(
            (existingAccount) -> {
                existingAccount.setName(updateAccount.getName());
                existingAccount.setEmail(updateAccount.getEmail());
                existingAccount.setPatientId(updateAccount.getPatientId());
                return billingAccountRepository.save(existingAccount);
            }
        );
    }

    public boolean deleteBillingAccount(UUID id){
        if(billingAccountRepository.existsById(id)){
            billingAccountRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

}
