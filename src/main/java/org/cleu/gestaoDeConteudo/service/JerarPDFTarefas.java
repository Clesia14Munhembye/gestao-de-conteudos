package org.cleu.gestaoDeConteudo.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JerarPDFTarefas {

    public void gerarRelatorio(List<TarefaDTO> tarefas, String nome, String email,Date hoje) throws JRException {
        // Compila o arquivo .jrxml (coloque o arquivo no diretório resources)
        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/relatorio_tarefas.jrxml"));

        // Define os parâmetros do relatório
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nome", nome);
        parametros.put("email", email);
        parametros.put("dataHoje", new Date());

        // Fonte de dados do relatório
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tarefas);

        // Preenche o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        // Exporta o relatório para PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio_tarefas.pdf");

        System.out.println("Relatório gerado com sucesso!");
    }
}
