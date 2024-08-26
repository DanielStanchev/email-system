package com.tinqinacademy.email.api.base;

import com.tinqinacademy.email.api.exceptionmodel.ErrorWrapper;
import io.vavr.control.Either;

public interface OperationProcessor<O extends OperationOutput, I extends OperationInput> {
    Either<ErrorWrapper, O> process(I input);
}
