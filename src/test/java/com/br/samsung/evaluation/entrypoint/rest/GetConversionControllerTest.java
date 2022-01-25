package com.br.samsung.evaluation.entrypoint.rest;


import com.br.samsung.evaluation.domain.model.GetConversionResponseTest;
import com.br.samsung.evaluation.domain.usecase.conversion.GetConversionUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GetConversionController.class)
public class GetConversionControllerTest {

    @MockBean
    private GetConversionUseCaseImpl useCase;

    @Autowired
    MockMvc mvc;

    @Test
    void test_get_conversion_isNoContent() throws Exception {

        when(useCase.execute(any(), any(), any(), any())).thenReturn(new ArrayList<>());

        mvc.perform(get("/samsung-evaluation/conversion?documentNumber=800002014&currencyCode=BRL"))
                .andExpect(status().isNoContent());
    }

    @Test
    void test_get_conversion_isOk() throws Exception {
       var response = GetConversionResponseTest.create();

        when(useCase.execute(any(), any(), any(), any())).thenReturn(Arrays.asList(response));

        mvc.perform(get("/samsung-evaluation/conversion?documentNumber=800002014&currencyCode=BRL"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].documentNumber").isNotEmpty());
    }
}
