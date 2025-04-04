package com.medsync.patientservice.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class BillingGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcClient.class);

    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingGrpcClient(@Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int serverPort) {
        log.info("Connecting to Billing Service {}:{}", serverAddress, serverPort);

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(serverAddress, serverPort)
                .usePlaintext()
                .build();

        blockingStub = BillingServiceGrpc.newBlockingStub(managedChannel);
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email) {
        BillingRequest billingRequest = BillingRequest.newBuilder().setEmail(email).setName(name)
                .setPatientId(patientId).build();
        BillingResponse billingResponse = blockingStub.createBillingAccount(billingRequest);
        log.info("Response recieved from GRPC billing service: {}", billingResponse.toString());
        return billingResponse;
    }

}
