public class Multi {
    public Monomial monomult(Monomial monomial1, Monomial monomial2) {
        Monomial result = new Monomial();
        result.setCoefficient(monomial1.getCoefficient().multiply(monomial2.getCoefficient()));
        for (String string : monomial1.getVariables()) {
            if (!result.getVariables().contains(string)) {
                result.getVariables().add(string);
                result.getIndexTable().put(string, monomial1.getIndexTable().get(string));
            } else {
                result.getIndexTable().put(string,
                        result.getIndexTable().get(string) + monomial1.getIndexTable().get(string));
            }
        }
        for (String string : monomial2.getVariables()) {
            if (!result.getVariables().contains(string)) {
                result.getVariables().add(string);
                result.getIndexTable().put(string, monomial2.getIndexTable().get(string));
            } else {
                result.getIndexTable().put(string,
                        result.getIndexTable().get(string) + monomial2.getIndexTable().get(string));
            }
        }
        for (TriFac triFac : monomial1.getTriFacs()) {
            if (!result.getTriFacs().isEmpty()) {
                result.getTriFacs().add(triFac);
            } else {
                int flag = 0;
                for (TriFac triFac1 : result.getTriFacs()) {
                    if (triFac1.getExpPoly().equals(triFac.getExpPoly())) {
                        triFac1.setIndex(triFac.getIndex() + triFac1.getIndex());
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    result.getTriFacs().add(triFac);
                }
            }
        }
        for (TriFac triFac : monomial2.getTriFacs()) {
            if (!result.getTriFacs().isEmpty()) {
                result.getTriFacs().add(triFac);
            } else {
                int flag = 0;
                for (TriFac triFac1 : result.getTriFacs()) {
                    if (triFac1.getExpPoly().equals(triFac.getExpPoly())) {
                        triFac1.setIndex(triFac.getIndex() + triFac1.getIndex());
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    result.getTriFacs().add(triFac);
                }
            }
        }
        return result;
    }
}
