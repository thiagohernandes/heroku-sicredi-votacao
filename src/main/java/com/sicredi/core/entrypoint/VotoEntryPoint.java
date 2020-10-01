package com.sicredi.core.entrypoint;

import com.sicredi.core.handler.exception.HandlerValidationException;
import com.sicredi.core.usecase.VotoUseCase;
import com.sicredi.core.usecase.http.VotoComputadoHttp;
import com.sicredi.core.usecase.http.VotoHttp;
import com.sicredi.dataprovider.mapper.VotoMapper;
import com.sicredi.dataprovider.timer.SessaoTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;

@RestController
@RequestMapping("${app.api-url-pautas}")
public class VotoEntryPoint {

    private final VotoUseCase votoUseCase;

    @Autowired
    public VotoEntryPoint(final VotoUseCase votoUseCase) {
        this.votoUseCase = votoUseCase;
    }

    @PostMapping("/computar-voto")
    public ResponseEntity<VotoHttp> computarVoto(@RequestBody VotoHttp votoHttp) throws HandlerValidationException {
        if (!SessaoTimer.controleSessao) {
            throw new HandlerValidationException("A sessão já está fechada para a pauta:  ".
                    concat(votoHttp.getPauta().getDescricao()));
        }
        if (votoUseCase.verificaVotoAssociado(votoHttp.getPauta().getId(), votoHttp.getCpf()) > 0) {
            throw new EntityExistsException("Voto já computado para o CPF " + votoHttp.getCpf());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(VotoMapper.entityToHttp(votoUseCase.computarVoto(votoHttp)));
    }

    @GetMapping("/{id-pauta}/resultado-votacao")
    public ResponseEntity<List<VotoComputadoHttp>> cadastrarPauta(@PathVariable("id-pauta") Long idPauta) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(votoUseCase.votosComputadosPorPauta(idPauta));
    }

}
