package com.sicredi.dataprovider;

import com.sicredi.core.usecase.http.PautaHttp;
import com.sicredi.dataprovider.entity.Pauta;
import com.sicredi.dataprovider.gateway.PautaGateway;
import com.sicredi.dataprovider.mapper.PautaMapper;
import com.sicredi.dataprovider.repository.PautaRepository;
import com.sicredi.dataprovider.timer.SessaoTimer;
import com.sicredi.dataprovider.util.PautaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Timer;

@Component
public class PautaDataProvider implements PautaGateway  {

    private final PautaRepository pautaRepository;
    private final String msgErro = "Pauta não encontrada!";

    @Autowired
    public PautaDataProvider(final PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta cadastrarPauta(PautaHttp pauta) {
        return pautaRepository.saveAndFlush(PautaMapper.httpToEntity(pauta));
    }

    public Pauta buscarPorId(Long id) {
        Optional<Pauta> pauta = pautaRepository.findById(id);
        if(pauta.isPresent()) {
            return pauta.get();
        } else {
            throw new EntityNotFoundException(msgErro);
        }
    }

    public String abrirSessao(Long idPauta, Integer periodoMinutos) {
        int minutos = periodoMinutos == null ? 1 : periodoMinutos;
        Timer timer = new Timer();
        timer.schedule(new SessaoTimer(idPauta, PautaUtil.minutosToSegundos(minutos)),0, 1000);
        return String.valueOf(minutos).concat(" minuto(s)")
                .concat(" para votação da pauta: ").
                        concat(String.valueOf(idPauta));

    }

}
