package com.example.calculador_sesi_app_01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;





public class MainActivity extends AppCompatActivity {

    //Variáveis de definição
    private TextView display; //Tela da calculadora
    private String currentInput = ""; //Entrada de vírgula, número etc
    private String currentOperator = ""; //Entrada dos operadores(+, -, x, /)
    private double operand1 = Double.NaN; //Operador 1
    private double operand2; // Operador 2
    private boolean resetScreen = false; //Ativo ou Inativo
    private double memory = 0.0; //Memória, 0.0 = inicia na calculadora


    @Override // Supermétodo(verifica se há algo errado no código)
    //Método de criação
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Variáveis de uso
        //Display
        display = findViewById(R.id.display);

        //Lista de botões numéricos
        int[] numberButtonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        };
        //Loop/Laço de repetição for = loop para sempre
        //pega a lista int e procura no front
        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(v ->
                    onNumberButtonClick(((Button) v).getText().toString()));
        }

        //Virgula
        findViewById(R.id.btn_point).setOnClickListener(v -> onDecimalButtonClick("."));
        //Operadores
        findViewById(R.id.btn_plus).setOnClickListener(v -> onOperatorButtonClick("+")); // Mais
        findViewById(R.id.btn_minus).setOnClickListener(v -> onOperatorButtonClick("-")); // Menos
        findViewById(R.id.btn_multiply).setOnClickListener(v -> onOperatorButtonClick("x")); //Vezes
        findViewById(R.id.btn_divide).setOnClickListener(v -> onOperatorButtonClick("/")); // Dividir

        //Igual
        findViewById(R.id.btn_equals).setOnClickListener(v -> onEqualsButtonClick());

        //Funções Especiais
        findViewById(R.id.btn_plus_minus).setOnClickListener(v -> onSignButtonClick()); // Mais e Menos
        findViewById(R.id.btn_percent).setOnClickListener(v -> onPercentButtonClick()); // Porcentagem
        findViewById(R.id.btn_c).setOnClickListener(v -> onClearButtonClick()); // Limpar a conta
        findViewById(R.id.btn_ce).setOnClickListener(v -> onClearEntryButtonClick()); // Limpar a tela
        findViewById(R.id.btn_backspace).setOnClickListener(v -> onBackspaceButtonClick()); // Backspace
        findViewById(R.id.btn_reciprocal).setOnClickListener(v -> onReciprocalButtonClick()); //
        findViewById(R.id.btn_square).setOnClickListener(v -> onSquareButtonClick()); // Elevado ao quadrado
        findViewById(R.id.btn_cube_root).setOnClickListener(v -> onCubeRootButtonClick()); // Elevado ao cubo

        //Memoria
        findViewById(R.id.btn_mc).setOnClickListener(v -> onMemoryClearButtonClick()); //Limpar a memória
        findViewById(R.id.btn_mr).setOnClickListener(v -> onMemoryRecallButtonClick()); // Chamar a memória
        findViewById(R.id.btn_m_plus).setOnClickListener(v -> onMemoryADDButtonClick()); // Faz conta de adição com o número da memória
        findViewById(R.id.btn_ms).setOnClickListener(v -> onMemorySUBTRACTButtonClick()); // Faz conta de subtrai com o número da memória
        findViewById(R.id.btn_ms).setOnClickListener(v -> onMemorySTOREButtonClick()); //Guarda o valor
        findViewById(R.id.btn_mv).setOnClickListener(v -> onMemoryViewButtonClick()); // Vizualição de memória


    }//Números

    private void onNumberButtonClick(String digit) {
        if (resetScreen) {
            currentInput = "";
            resetScreen = false;
        }

        if (currentInput.equals("0") && digit.equals("0")) {
            return;
        }

        if (currentInput.equals("0") && !digit.equals("0")){

    } else

    {
        currentInput += digit;
    }
        updateDisplay();
}

private void onDecimalButtonClick(String digit){
        if(resetScreen){
            currentInput = "0.";
            resetScreen = false;
            updateDisplay();
            return;
        }

        if(currentInput.contains(".")){
            return;
        }
        if(currentInput.isEmpty()){
            currentInput = "0.";
        }
        updateDisplay();
}

      //Operadores

       private void onOperatorButtonClick(String operator){
        if(!Double.isNaN(operand1)){
            onEqualsButtonClick();
        }
        try{
            operand1 = Double.parseDouble(currentInput);
        } catch (NumberFormatException e){
            operand1 = 0.0;
        }
        currentOperator = operator;
        resetScreen = true;
       }


       private void onEqualsButtonClick(){
        if(Double.isNaN(operand1) || currentOperator.isEmpty()){
            return;
        }
        try{
            operand2 = Double.parseDouble(currentInput);
        } catch (NumberFormatException e){
            operand2 = 0.0;
        }

        double result = 0.0;

        switch (currentOperator){
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "x":
                result = operand1 * operand2;
                break;
            case "/":
                if(operand2 == 0){
                    display.setText("ERROR");
                    resetCalculator();
                    return;
                }
                result = operand1 / operand2;
                break;

        }
        currentInput = formatResult(result);
        operand1 = result;
        resetScreen = true;
        currentOperator ="";
        updateDisplay();
       }

       private String formatResult(double result) {
           if (result == (long) result) {
               return String.valueOf((long) result);
           } else {
           return String.format("%.10f", result)
                   .replaceAll( "0*$", "")
                   .replaceAll("\\.$", "");
           }

    }

    //Funções especiais
    private void onSignButtonClick(){
        if(currentInput.isEmpty() || currentInput.equals("0")) return;
        double value = Double.parseDouble(currentInput);
        value = -value;
        currentInput = formatResult(value);
        updateDisplay();

    }
   //Percentual
    private void onPercentButtonClick(){
        if(currentInput.isEmpty()) return;
        double value = Double.parseDouble(currentInput);
        value = value / 100;
        currentInput = formatResult(value);
        updateDisplay();

    }

    private void onClearButtonClick(){
        resetCalculator();
        updateDisplay();
    }

    private void onClearEntryButtonClick(){
       currentInput = "0";
        updateDisplay();
    }

    private void onBackspaceButtonClick() {
        if (currentInput.length() > 0) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
        }
        if(currentInput.isEmpty() || currentInput.equals("-")){
            currentInput = "0";
        }
        updateDisplay();
    }
    private void onReciprocalButtonClick(){
        if(currentInput.isEmpty() || currentInput.equals("0")) return;
        double value = Double.parseDouble(currentInput);
        if(value != 0){
            value = 1 / value;
            currentInput = formatResult(value);
            updateDisplay();
            resetScreen = true;
        } else{
            display.setText("ERROR");
            resetCalculator();
        }
    }
    private void onSquareButtonClick(){
        if(currentInput.isEmpty()) return;
        double value = Double.parseDouble(currentInput);
            value = value * value;
            currentInput = formatResult(value);
            updateDisplay();
            resetScreen = true;
        }
    private void onCubeRootButtonClick(){
        if(currentInput.isEmpty()) return;
        double value = Double.parseDouble(currentInput);
        value = Math.cbrt(value);
        currentInput = formatResult(value);
        updateDisplay();
        resetScreen = true;
    }
        //Memórias
       private void onMemoryClearButtonClick(){
        memory = 0.0;
        display.setText("mc");
       }
    private void onMemoryRecallButtonClick(){
        currentInput = formatResult(memory);
        updateDisplay();
        resetScreen = true;
    }
    private void onMemoryADDButtonClick(){
       try{
           double value = Double.parseDouble(currentInput);
           memory += value;
           display.setText("m+");

       }catch (NumberFormatException e){
           resetScreen = true;
           memory = 0.0;
       }
    }
    private void onMemorySTOREButtonClick () {
        try{
            memory -= Double.parseDouble(currentInput);
            display.setText("ms");
        }catch (NumberFormatException e){
            memory = 0.0;
        }
        resetScreen = true;
    }

    private void onMemorySUBTRACTButtonClick(){
        try{
            double value = Double.parseDouble(currentInput);
            display.setText("m");

        }catch (NumberFormatException e){
            memory = 0.0;
        }
        resetScreen = true;
    }
  private void onMemoryViewButtonClick(){
        display.setText("M: " + formatResult(memory));
  }

  //Auxiliares
    private void resetCalculator(){
        currentInput = "0";
        currentOperator = "";
        operand1 = Double.NaN;
        resetScreen = true;
    }
    private void updateDisplay(){
        display.setText(currentInput.isEmpty() ? "0": currentInput
        );
    }

}



