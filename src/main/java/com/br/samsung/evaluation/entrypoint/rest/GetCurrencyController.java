package com.br.samsung.evaluation.entrypoint.rest;

import com.br.samsung.evaluation.domain.usecase.currency.GetCurrencyUseCaseImpl;
import com.br.samsung.evaluation.entrypoint.rest.exception.ErrorResponse;
import com.br.samsung.evaluation.domain.dataprovider.response.GetCurrencyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/samsung-evaluation")
public class GetCurrencyController {

    private final GetCurrencyUseCaseImpl useCase;

    public GetCurrencyController(GetCurrencyUseCaseImpl useCase) {
        this.useCase = useCase;
    }

    @Operation(summary = "Get All currency")

    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GetCurrencyResponse.class))),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unsuccessful operation",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetCurrencyResponse>> getCurrency() {
        var response = this.useCase.execute();

        if(response != null && !response.isEmpty()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.noContent().build();
    }
}
