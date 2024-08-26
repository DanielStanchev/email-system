package com.tinqinacademy.email.core.processors;

import com.tinqinacademy.email.api.exceptionmodel.ErrorWrapper;
import com.tinqinacademy.email.api.operations.sendemail.SendEmail;
import com.tinqinacademy.email.api.operations.sendemail.SendEmailInput;
import com.tinqinacademy.email.api.operations.sendemail.SendEmailOutput;
import com.tinqinacademy.email.core.base.BaseOperationProcessor;
import com.tinqinacademy.email.core.exception.ErrorMapper;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

@Slf4j
@Service
public class SendEmailOperationProcessor extends BaseOperationProcessor implements SendEmail {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender emailSender;

    public SendEmailOperationProcessor(ConversionService conversionService, Validator validator,
                                       ErrorMapper errorMapper, JavaMailSender emailSender) {
        super(validator, conversionService,errorMapper);
        this.emailSender = emailSender;
    }

    @Override
    public Either<ErrorWrapper, SendEmailOutput> process(SendEmailInput input) {
        log.info("Start sendEmail input{}",input);
        return validateInput(input).flatMap(validated->sendEmail(input));
    }

    private Either<ErrorWrapper, SendEmailOutput> sendEmail(SendEmailInput input) {
        return Try.of(() -> {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(from);
                message.setTo(input.getTo());
                message.setSubject(input.getSubject());
                message.setText(input.getContent());
                emailSender.send(message);
                log.info("Email sent to {} from {}", input.getTo(), from);
                SendEmailOutput output = SendEmailOutput.builder().build();
                log.info("End sendEmail output{}", output);
                return output;

            })
            .toEither()
            .mapLeft(throwable -> Match(throwable).of(Case($(), errorMapper.handleError(throwable, HttpStatus.BAD_REQUEST))));
    }
}
