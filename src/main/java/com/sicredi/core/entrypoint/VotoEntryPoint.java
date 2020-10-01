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
@RequestMapping("/api/v1/pautas")
public class VotoEntryPoint {

    private final VotoUseCase votoUseCase;

    @Autowired
    public VotoEntryPoint(final VotoUseCase votoUseCase) {
        this.votoUseCase = votoUseCase;
    }

    @PostMapping("/computar-voto")
    public ResponseEntity<VotoHttp> computarVoto(@RequestBody VotoHttp votoHttp) throws HandlerValidationException {
        boolean valido = votoUseCase.validaCpf(votoHttp.getCpf());
        if (!valido) {
            throw new HandlerValidationException("CPF não válido para votar!");
        }
        if (!SessaoTimer.controleSessao) {
            throw new HandlerValidationException("A sessão já está fechada para a pauta:  ".
                    concat(votoHttp.getPauta().getDescricao()));
        }
        if (votoUseCase.verificaVotoAssociado(votoHttp.getPauta().getId(), votoHttp.getCpf()) > 0) {
            throw new HandlerValidationException("Voto já computado para o CPF "
                    .concat(votoHttp.getCpf().toString()));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(VotoMapper.entityToHttp(votoUseCase.computarVoto(votoHttp)));
    }

    @GetMapping("/{id-pauta}/resultado-votacao")
    public ResponseEntity<List<VotoComputadoHttp>> resultadoVotacaoPauta(@PathVariable("id-pauta") Long idPauta) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(votoUseCase.votosComputadosPorPauta(idPauta));
    }

    @GetMapping("/{cpf}/validar")
    public ResponseEntity<Boolean> validaCpf(@PathVariable("cpf") Long cpf) throws HandlerValidationException {
        boolean valido = votoUseCase.validaCpf(cpf);
        if (!valido) {
            throw new HandlerValidationException("CPF não válido para votar!");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(valido);
    }


}
