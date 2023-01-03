package com.guerra.exception;

import java.util.ArrayList;
import java.util.List;

public class ExportFelException extends Exception {

    private List<ExportFelException> exceptionsList;


    public ExportFelException(String message, List<ExportFelException> exceptionsList) {
        super(message);
        this.exceptionsList = exceptionsList;
    }

    public ExportFelException(String message, Throwable cause) {
        super(message, cause);
        this.exceptionsList = new ArrayList<>();
    }

    public ExportFelException(String message) {
        super(message);
        this.exceptionsList = new ArrayList<>();
    }

    public ExportFelException() {
        super("Error al exportar los archivos FEL");
        this.exceptionsList = new ArrayList<>();
    }

    public List<ExportFelException> getExceptionsList() {
        return exceptionsList;
    }

    public void addException(ExportFelException exception) {
        if (exceptionsList == null) {
            exceptionsList = new ArrayList<>();
        }
        exceptionsList.add(exception);
    }

    public boolean hasExceptions() {
        return exceptionsList != null && !exceptionsList.isEmpty();
    }

    public ExportFelException build() {

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(this.getMessage());

        for (ExportFelException exceptionItem : this.exceptionsList) {
            messageBuilder
                    .append("\n\t")
                    .append(exceptionItem.getMessage())
                    .append(" -> ")
                    .append(exceptionItem.getCause());
        }

        return new ExportFelException(messageBuilder.toString(), this.exceptionsList);
    }
}
