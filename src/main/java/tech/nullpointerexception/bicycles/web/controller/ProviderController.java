package tech.nullpointerexception.bicycles.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nullpointerexception.bicycles.services.ProviderService;
import tech.nullpointerexception.bicycles.util.UtilConstants;
import tech.nullpointerexception.bicycles.web.exception.NotFoundException;
import tech.nullpointerexception.bicycles.web.exception.ProviderException;
import tech.nullpointerexception.bicycles.web.model.ProviderDto;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/provider")
@RequiredArgsConstructor
@Validated
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/{providerDni}")
    @ResponseStatus(HttpStatus.OK)
    public ProviderDto getProvider(@PathVariable("providerDni") String providerDni) {
        return Optional.ofNullable(providerService.getProvider(providerDni))
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<ProviderDto> handleProviderCreation(@Valid @RequestBody ProviderDto provider) {
        ProviderDto savedProvider = Optional.ofNullable(providerService.createProvider(provider))
                .orElseThrow(() -> new ProviderException(UtilConstants.ERROR_WHILE_CREATING_PROVIDER));
        HttpHeaders headers = new HttpHeaders();
        //Todo: añadir hostname a header
        headers.add("Location", "/api/v1/provider/" + savedProvider.getId().toString());
        return new ResponseEntity<>(savedProvider, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{providerDni}")
    @ResponseStatus(HttpStatus.OK)
    public ProviderDto handleUpdateProvider(@PathVariable("providerDni") String providerDni,
                                            @Valid
                                            @RequestBody ProviderDto providerDto) {
        return Optional.ofNullable(providerService.updateProvider(providerDni, providerDto))
                .orElseThrow(() -> new ProviderException(UtilConstants.ERROR_WHILE_UPDATING_PROVIDER));
    }

    @DeleteMapping("/{providerDni}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDeleteProvider(@PathVariable("providerDni") String providerDni) {

        Boolean response = Optional.ofNullable(providerService.deleteByProviderDni(providerDni))
                .orElse(Boolean.FALSE);

        if (!response) {
            throw new ProviderException(UtilConstants.ERROR_WHILE_DELETING_PROVIDER);
        }
    }
}
