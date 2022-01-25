package com.br.samsung.evaluation.entrypoint.rest;

import com.br.samsung.evaluation.domain.dataprovider.response.GetConversionResponse;
import com.br.samsung.evaluation.domain.usecase.conversion.GetConversionUseCaseImpl;
import com.br.samsung.evaluation.entrypoint.rest.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/samsung-evaluation")
public class GetConversionController {

    private final GetConversionUseCaseImpl useCase;

    public GetConversionController(GetConversionUseCaseImpl useCase) {
        this.useCase = useCase;
    }

    @Operation(summary = "Get All conversion")

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = GetConversionResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unsuccessful operation",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    @GetMapping(value = "/conversion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetConversionResponse>> getConversion(@RequestParam(name = "documentNumber", required = false) String documentNumber,
                                                                     @RequestParam(name = "currencyCode") String currencyCode,
                                                                     @RequestParam(name = "startDate", required = false) String startDate,
                                                                     @RequestParam(name = "endDate", required = false) String endDate) {
        var response =
                this.useCase.execute(documentNumber, currencyCode, startDate, endDate);

        if(response != null && !response.isEmpty()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.noContent().build();
    }
}
