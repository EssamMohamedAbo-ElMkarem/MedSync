package com.medsync.billingservice.grpc;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.medsync.billingservice.models.BillingAccount;
import com.medsync.billingservice.models.BillingStatus;
import com.medsync.billingservice.services.BillingAccountService;

import billing.BillingAccountRequest;
import billing.BillingAccountResponse;
import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import billing.UpdateBillingRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Autowired
    private BillingAccountService billingAccountService;

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> respObserver) {
        log.info("Create account request received: {}", billingRequest.toString());

        BillingAccount billingAccount = new BillingAccount(
            UUID.fromString(billingRequest.getPatientId()),
            billingRequest.getName(),
            billingRequest.getEmail(),
            BillingStatus.ACTIVE
        );

        BillingAccount savedAccount = billingAccountService.createBillingAccount(billingAccount);

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId(savedAccount.getId().toString())
                .setStatus(savedAccount.getStatus().name())
                .build();

        respObserver.onNext(response);
        respObserver.onCompleted();
    }

    @Override
    public void getBillingAccount(BillingAccountRequest billingAccountRequest,
                                  StreamObserver<BillingAccountResponse> respObserver) {
        log.info("Get account request received: {}", billingAccountRequest.toString());

        UUID accountId = UUID.fromString(billingAccountRequest.getAccountId());
        Optional<BillingAccount> account = billingAccountService.getBillingAccount(accountId);

        if (account.isPresent()) {
            BillingAccountResponse response = BillingAccountResponse.newBuilder()
                    .setAccountId(account.get().getId().toString())
                    .setName(account.get().getName())
                    .setEmail(account.get().getEmail())
                    .setStatus(account.get().getStatus().name())
                    .setPatientId(account.get().getPatientId().toString())
                    .build();

            respObserver.onNext(response);
        } else {
            respObserver.onError(new RuntimeException("Billing account not found."));
        }
        respObserver.onCompleted();
    }

    @Override
    public void updateBillingAccount(UpdateBillingRequest updateBillingRequest,
                                     StreamObserver<BillingResponse> respObserver) {
        log.info("Update account request received: {}", updateBillingRequest.toString());

        UUID accountId = UUID.fromString(updateBillingRequest.getAccountId());
        Optional<BillingAccount> existingAccount = billingAccountService.getBillingAccount(accountId);

        if (existingAccount.isPresent()) {
            BillingAccount updatedAccount = existingAccount.get();
            updatedAccount.setName(updateBillingRequest.getName());
            updatedAccount.setEmail(updateBillingRequest.getEmail());

            BillingAccount savedAccount = billingAccountService.updateBillingAccount(accountId, updatedAccount).orElseThrow();

            BillingResponse response = BillingResponse.newBuilder()
                    .setAccountId(savedAccount.getId().toString())
                    .setStatus(savedAccount.getStatus().name())
                    .build();

            respObserver.onNext(response);
        } else {
            respObserver.onError(new RuntimeException("Billing account not found."));
        }
        respObserver.onCompleted();
    }

    @Override
    public void deleteBillingAccount(BillingAccountRequest billingAccountRequest,
                                     StreamObserver<BillingResponse> respObserver) {
        log.info("Delete account request received: {}", billingAccountRequest.toString());

        UUID accountId = UUID.fromString(billingAccountRequest.getAccountId());

        if (billingAccountService.deleteBillingAccount(accountId)) {
            BillingResponse response = BillingResponse.newBuilder()
                    .setAccountId(accountId.toString())
                    .setStatus("DELETED")
                    .build();
            respObserver.onNext(response);
        } else {
            respObserver.onError(new RuntimeException("Billing account not found."));
        }
        respObserver.onCompleted();
    }
}
