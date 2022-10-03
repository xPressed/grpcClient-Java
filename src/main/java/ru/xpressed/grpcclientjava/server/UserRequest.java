package ru.xpressed.grpcclientjava.server;

import grpc.xpressed.UserServiceGrpc;
import grpc.xpressed.UserServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UserRequest {
    public static boolean add(String username, String password) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        UserServiceOuterClass.UserAddRequest request = UserServiceOuterClass.UserAddRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();

        UserServiceOuterClass.UserAddResponse response = stub.addUser(request);
        channel.shutdownNow();
        return response.getStatus();
    }

    public static String[] get(String username) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();

        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        UserServiceOuterClass.UserGetRequest request = UserServiceOuterClass.UserGetRequest.newBuilder()
                .setUsername(username).build();

        UserServiceOuterClass.UserGetResponse response = stub.getUser(request);
        channel.shutdownNow();
        return new String[] {response.getUsername(), response.getPassword()};
    }

    public static boolean delete(String username) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

        UserServiceOuterClass.UserDeleteRequest request = UserServiceOuterClass.UserDeleteRequest.newBuilder()
                .setUsername(username).build();

        UserServiceOuterClass.UserDeleteResponse response = stub.deleteUser(request);
        channel.shutdownNow();
        return response.getStatus();
    }
}
