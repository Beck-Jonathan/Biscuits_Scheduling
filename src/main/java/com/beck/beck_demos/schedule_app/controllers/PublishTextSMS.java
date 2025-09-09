package com.beck.beck_demos.schedule_app.controllers;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

/**
 * Before running this Java V2 code example, set up your development
 * environment, including your credentials.
 *
 * For more information, see the following documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class PublishTextSMS {
  public static void main(String[] args) {
    final String usage = " Usage:    <message> <phoneNumber>"+
        " Where:"+
        " message - The message text to send."+
        "phoneNumber - The mobile phone number to which a message is sent (for example, +1XXX5550100).";

    //if (args.length != 2) {
    //  System.out.println(usage);
    //  System.exit(1);
    //}

    String message = "test";
    String phoneNumber = "+15635433438";
    SnsClient snsClient = SnsClient.builder()
        .region(Region.US_EAST_1)
        .build();
    pubTextSMS(snsClient, message, phoneNumber);
    snsClient.close();
  }

  public static void pubTextSMS(SnsClient snsClient, String message, String phoneNumber) {
    try {
      PublishRequest request = PublishRequest.builder()
          .message(message)
          .phoneNumber(phoneNumber)
          .build();

      PublishResponse result = snsClient.publish(request);
      System.out
          .println(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());

    } catch (SnsException e) {
      System.err.println(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }
}
