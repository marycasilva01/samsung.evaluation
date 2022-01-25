package com.br.samsung.evaluation.domain.dataprovider.response;

import com.br.samsung.evaluation.dataprovider.client.model.CurrencyDTO;
import lombok.Data;

@Data
public class GetCurrencyResponse {
    private Long currencyId;
    private String currencyCode;
    private String currencyDesc;

    public GetCurrencyResponse(CurrencyDTO dto){
        this.currencyCode = dto.getCurrencyCode();
        this.currencyDesc = dto.getCurrencyDesc();
        this.currencyId = dto.getCurrencyId();
    }
}
