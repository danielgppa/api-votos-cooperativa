package br.com.meta.apivotoscooperativa.dto;

public class ResultadoVotacaoDTO {
    private Long totalSim;
    private Long totalNao;

    public Long getTotalSim() {
        return totalSim;
    }

    public void setTotalSim(Long totalSim) {
        this.totalSim = totalSim;
    }

    public Long getTotalNao() {
        return totalNao;
    }

    public void setTotalNao(Long totalNao) {
        this.totalNao = totalNao;
    }
}
