package com.tinqinacademy.email.kafka.consumer;

import com.tinqinacademy.email.api.exceptionmodel.ErrorWrapper;
import com.tinqinacademy.email.api.operations.sendemail.SendEmail;
import com.tinqinacademy.email.api.operations.sendemail.SendEmailInput;
import com.tinqinacademy.email.api.operations.sendemail.SendEmailOutput;
import com.tinqinacademy.email.kafka.model.EmailMessage;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEmailConsumer {

    private final SendEmail sendEmail;

    @KafkaListener(id = "consumerId", topics = "email", containerFactory = "kafkaListenerContainerFactory")
    public void listen(EmailMessage message) {
        SendEmailInput input = SendEmailInput.builder()
            .to(message.getTo())
            .subject(message.getSubject())
            .content(message.getContent())
            .build();

        Either<ErrorWrapper, SendEmailOutput> result = sendEmail.process(input);
        if (result.isLeft()) {
            log.error(result.getLeft()
                          .toString());
        }
    }
}
