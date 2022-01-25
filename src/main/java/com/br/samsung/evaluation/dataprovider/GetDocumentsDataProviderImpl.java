package com.br.samsung.evaluation.dataprovider;

import com.br.samsung.evaluation.dataprovider.client.EvaluationAPI;
import com.br.samsung.evaluation.dataprovider.client.model.DocsDTO;
import com.br.samsung.evaluation.domain.dataprovider.GetDocumentsDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDocumentsDataProviderImpl implements GetDocumentsDataProvider {

    private final EvaluationAPI api;

    @Override
    public List<DocsDTO> execute(String documentNumber, String  currencyCode, LocalDate startDate, LocalDate endDate) {
        List<Predicate<DocsDTO>> allPredicates = new ArrayList<>();

        if(Objects.nonNull(currencyCode)){
            allPredicates.add(doc -> doc.getCurrencyCode().equals(currencyCode));
        }
        if (Objects.nonNull(documentNumber)) {
            allPredicates.add(doc -> doc.getDocumentNumber().equals(documentNumber));
        }
        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            allPredicates.add(doc -> (doc.getDocumentDate().isAfter(startDate) || doc.getDocumentDate().isEqual(startDate))
                    && (doc.getDocumentDate().isBefore(endDate) || doc.getDocumentDate().isEqual(endDate)));
        }

        return api.getDocs().stream().filter(allPredicates.stream().reduce(x->true, Predicate::and))
                .collect(Collectors.toList());
    }
}
