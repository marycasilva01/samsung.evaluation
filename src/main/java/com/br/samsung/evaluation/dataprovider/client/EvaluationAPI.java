package com.br.samsung.evaluation.dataprovider.client;

import com.br.samsung.evaluation.dataprovider.client.model.CurrencyDTO;
import com.br.samsung.evaluation.dataprovider.client.model.DocsDTO;
import com.br.samsung.evaluation.dataprovider.client.model.QuotationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "evaluationAPI",
        url = "${url.feign.client}"
)
public interface EvaluationAPI {

    @GetMapping("/currency")
    List<CurrencyDTO> getCurrency();

    @GetMapping("/quotation")
    List<QuotationDTO> getQuotation();

    @GetMapping("/docs")
    List<DocsDTO> getDocs();
}
