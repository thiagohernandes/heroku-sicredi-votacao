package com.sicredi.core.entrypoint;

import com.sicredi.core.usecase.PautaUseCase;
import com.sicredi.core.usecase.http.PautaHttp;
import com.sicredi.dataprovider.mapper.PautaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.api-url-pautas}")
public class PautaEntryPoint {

    private final PautaUseCase pautaUseCase;

    @Autowired
    public PautaEntryPoint(final PautaUseCase pautaUseCase) {
        this.pautaUseCase = pautaUseCase;
    }

    @PostMapping()
    public ResponseEntity<PautaHttp> cadastrarPauta(@RequestBody PautaHttp pautaHttp) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PautaMapper.entityToHttp(pautaUseCase.cadastrarPauta(pautaHttp)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaHttp> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(PautaMapper.entityToHttp(pautaUseCase.buscarPorId(id)));
    }

    @PostMapping("/abrir-sessao")
    public ResponseEntity<String> abrirSessao(@RequestParam(required = true) Long idPauta,
                                              @RequestParam(required = false) Integer periodoMinutos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pautaUseCase.abrirSessao(idPauta, periodoMinutos));
    }

}
