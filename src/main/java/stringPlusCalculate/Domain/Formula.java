package stringPlusCalculate.Domain;


import stringPlusCalculate.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Formula {

    final String formula;

    private Formula(String formula) {
        this.formula = formula;
    }

    public static Formula init(String formula) {
        return new Formula(formula);
    }

    public ArithmeticTargets arithmeticBySplit() {
        List<String> splitters = determineSplitter();

        String trimArithmetic = trimArithmetic(splitters);
        List<String> arithmetic = new ArrayList<>(Arrays.asList(trimArithmetic.split(Constants.EMPTY_STRING)));

        return ArithmeticTargets.createByStringList(arithmetic);
    }

    List<String> determineSplitter() {
        String[] splitters = new String[]{":", ","};

        if(isSplitter()) {
            splitters = new String[]{findSplitter()};
        }

        return Arrays.asList(splitters);
    }

    boolean isSplitter() {
        return formula.matches(Constants.SPLITTER_EXISTENCE_REGEX);
    }

    String findSplitter() {
        int splitterStart = formula.indexOf(Constants.SPLITTER_DIVIDER_START) + Constants.SPLITTER_DIVIDER_START.length();
        int splitterEnd = formula.indexOf(Constants.SPLITTER_DIVIDER_END);
        return formula.substring(splitterStart, splitterEnd);
    }

    String trimArithmetic(List<String> splitters) {
        String pureArithmetic = formula;
        for (String splitter : splitters) {
            pureArithmetic = formula.replace(splitter, "");
        }
        return pureArithmetic;
    }

    @Override
    public String toString() {
        return formula;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.toString().equals(((Formula) obj).toString());
    }
}
