package com.abernathy.mediscreen.mdto.service;

import com.abernathy.mediscreen.mdto.exception.DateFormatException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DtoDateUtilsTest {

    @Test
    void stringToDateTest() throws DateFormatException {
        // Arrange
        String strDate = "2012-12-21";
        LocalDate expectedDate = LocalDate.of(2012, 12, 21);
        // Act
        LocalDate result = DtoDateUtils.stringToDate(strDate);
        // Assert
        assertThat(result).isEqualTo(expectedDate);
    }

    @Test
    void stringToDateWithDateFormatExceptionTest() {
        // Arrange
        String badStrDate = "2012-12-32";
        // Act + Assert
        assertThrows(DateFormatException.class, () -> DtoDateUtils.stringToDate(badStrDate));
    }

    @Test
    void dateToStringTest() {
        // Arrange
        LocalDate testDate = LocalDate.of(2012, 12, 21);
        String expectedResult = "2012-12-21";
        // Act
        String result = DtoDateUtils.dateToString(testDate);
        // Arrange
        assertThat(result).isEqualTo(expectedResult);
    }

}
