package com.guerra.exception;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Log4j2
class ExportFelExceptionTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void testExeption() throws ExportFelException {

        try {

            var exception = new ExportFelException();


            exception.addException(new ExportFelException("El archivo 1 no se pudo exportar", new IOException()));
            exception.addException(new ExportFelException("El archivo 2 no se pudo exportar", new IOException("Descripcion de la excepcion")));

            throw exception.build();
        } catch (ExportFelException e) {
            log.error("Error al exportar los archivos\n" + e.getMessage(), e);

        }


    }
}