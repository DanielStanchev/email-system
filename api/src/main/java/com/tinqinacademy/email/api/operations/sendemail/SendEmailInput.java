package com.tinqinacademy.email.api.operations.sendemail;

import com.tinqinacademy.email.api.base.OperationInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SendEmailInput implements OperationInput {
    private String to;
    private String subject;
    private String content;
}
