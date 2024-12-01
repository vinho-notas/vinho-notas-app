#!/bin/bash

# Função para executar o Pitest
function run_all_tests() {
  mvn test-compile org.pitest:pitest-maven:mutationCoverage -DoutputFormats=XML,HTML -DwithHistory -DtimestampedReports=false -DmutationThreshold=80 -Dverbose=true -DtimeoutFactor=2
  open_report "target/pit-reports/index.html"
}

# Função para abrir o relatório no navegador
function open_report() {
  reportPath=$1
  if [ -f "$reportPath" ]; then
#    O comando xdg-open funciona em sistemas Linux. Para sistemas MacOS, utilize o comando open. Em sistemas Windows, utilize start.
#    xdg-open "$reportPath" &>/dev/null || open "$reportPath" &>/dev/null || echo "Não foi possível abrir o relatório automaticamente."
  start "$reportPath" &>/dev/null || open "$reportPath" &>/dev/null || echo "Não foi possível abrir o relatório automaticamente."
  else
    echo "Relatório não encontrado em $reportPath."
  fi
}

# Executa a função para executar os testes
run_all_tests

# Fim do script
