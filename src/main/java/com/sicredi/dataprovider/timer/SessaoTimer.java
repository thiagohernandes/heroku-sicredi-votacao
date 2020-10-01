package com.sicredi.dataprovider.timer;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

@Slf4j
public class SessaoTimer extends TimerTask {

    public Integer segundosAtual = 0;
    public Integer totalSegundos = 0;
    private final String msgSessaoAberta = "Sessão em execução... ";
    private final String msgSessaoFechada = "Sessão fechada!";
    private final String msgIdPautaExecucao = "Id da pauta em votação: ";
    public static boolean controleSessao = false;
    public Long idPautaEmVotacao = 0L;

    public SessaoTimer(Long idPauta, Integer totalSegundos){
        this.totalSegundos = totalSegundos;
        this.idPautaEmVotacao = idPauta;
    }

    public void run() {
        controleSessao = true;
        log.info(msgIdPautaExecucao, idPautaEmVotacao);
        segundosAtual += 1;
        if (totalSegundos > segundosAtual) {
            log.info(msgSessaoAberta, segundosAtual);
        } else if (totalSegundos == segundosAtual) {
            controleSessao = false;
            log.info(msgSessaoAberta + segundosAtual);
            log.info(msgSessaoFechada);
            cancel();
        }
    }
}
