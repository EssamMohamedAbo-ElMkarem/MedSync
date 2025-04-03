package com.medsync.billingservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medsync.billingservice.models.BillingAccount;

@Repository
public interface BillingAccountRepository extends JpaRepository<BillingAccount, UUID> {

}
