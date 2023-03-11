import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Monomial {
    private BigInteger coefficient = new BigInteger("0");
    private ArrayList<String> variables = new ArrayList<>();
    private HashMap<String, Integer> indexTable = new HashMap<>();
    private ArrayList<TriFac> triFacs = new ArrayList<>();

    public BigInteger getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigInteger coefficient) {
        this.coefficient = coefficient;
    }

    public ArrayList<String> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<String> variables) {
        this.variables = variables;
    }

    public HashMap<String, Integer> getIndexTable() {
        return indexTable;
    }

    public void setIndexTable(HashMap<String, Integer> indexTable) {
        this.indexTable = indexTable;
    }

    public ArrayList<TriFac> getTriFacs() {
        return triFacs;
    }

    public void setTriFacs(ArrayList<TriFac> triFacs) {
        this.triFacs = triFacs;
    }

    public Monomial(BigInteger coefficient, ArrayList<String> variables,
                    HashMap<String, Integer> indexTable, ArrayList<TriFac> triFacs) {
        this.coefficient = coefficient;
        this.variables = variables;
        this.indexTable = indexTable;
        this.triFacs = triFacs;
    }

    public Monomial() {

    }

    public boolean equals(Monomial mono) {
        if (!this.coefficient.equals(mono.getCoefficient())) {
            return false;
        }
        if (this.variables.size() != mono.variables.size()) {
            return false;
        }
        if (!this.getVariables().isEmpty() && !mono.getVariables().isEmpty()) {
            int signal = 1;
            for (String string1 : this.getVariables()) {
                if (!mono.getVariables().contains(string1)
                        || this.getIndexTable().get(string1) !=
                        mono.getIndexTable().get(string1)) {
                    signal = 0;
                    break;
                }
            }
            if (signal == 0) {
                return false;
            }
        }
        if (this.triFacs.size() != mono.triFacs.size()) {
            return false;
        }
        if (!this.triFacs.isEmpty() && !mono.triFacs.isEmpty()) {
            int cnt1 = 0;
            int cnt2 = 0;
            int cnt3 = 0;
            int cnt4 = 0;
            for (TriFac triFac : this.triFacs) {
                if (triFac.getType() == 1) {
                    cnt1++;
                } else {
                    cnt2++;
                }
            }
            for (TriFac triFac : mono.triFacs) {
                if (triFac.getType() == 1) {
                    cnt3++;
                } else {
                    cnt4++;
                }
            }
            if (cnt1 != cnt3 || cnt2 != cnt4) {
                return false;
            }
            return judge(this.triFacs, mono.triFacs);
        }
        return true;
    }

    public boolean like(Monomial mono) {
        if (this.variables.size() != mono.variables.size()) {
            return false;
        }
        if (!this.getVariables().isEmpty() && !mono.getVariables().isEmpty()) {
            int signal = 1;
            for (String string1 : this.getVariables()) {
                if (!mono.getVariables().contains(string1)
                        || this.getIndexTable().get(string1) !=
                        mono.getIndexTable().get(string1)) {
                    signal = 0;
                    break;
                }
            }
            if (signal == 0) {
                return false;
            }
        }
        if (this.triFacs.size() != mono.triFacs.size()) {
            return false;
        }
        if (!this.triFacs.isEmpty() && !mono.triFacs.isEmpty()) {
            int cnt1 = 0;
            int cnt2 = 0;
            int cnt3 = 0;
            int cnt4 = 0;
            for (TriFac triFac : this.triFacs) {
                if (triFac.getType() == 1) {
                    cnt1++;
                } else {
                    cnt2++;
                }
            }
            for (TriFac triFac : mono.triFacs) {
                if (triFac.getType() == 1) {
                    cnt3++;
                } else {
                    cnt4++;
                }
            }
            if (cnt1 != cnt3 || cnt2 != cnt4) {
                return false;
            }
            return judge(this.triFacs, mono.triFacs);
        }
        return true;
    }

    public boolean judge(ArrayList<TriFac> triFacs1, ArrayList<TriFac> triFacs2) {
        ArrayList<TriFac> temp1 = new ArrayList<>();
        ArrayList<TriFac> temp2 = new ArrayList<>();
        temp1.addAll(triFacs1);
        temp2.addAll(triFacs2);
        for (TriFac triFac1 : triFacs1) {
            for (TriFac triFac2 : triFacs2) {
                if (triFac1.getIndex() == triFac2.getIndex() &&
                        triFac1.getExpPoly().equals(triFac2.getExpPoly())) {
                    temp1.remove(triFac1);
                    temp2.remove(triFac2);
                    break;
                }
            }
        }
        return temp1.isEmpty() && temp2.isEmpty();
    }

    public String toString() {
        this.checkEmptyVars();
        StringBuilder sb = new StringBuilder();
        if (this.coefficient.equals(new BigInteger("0"))) {
            sb.append("0");
        } else {
            if (this.getVariables().isEmpty() && this.triFacs.isEmpty()) {
                sb.append(this.getCoefficient());
            } else {
                if (this.getCoefficient().equals(new BigInteger("1"))) {
                    sb.append("");
                } else if (this.getCoefficient().equals(new BigInteger("-1"))) {
                    sb.append("-");
                } else {
                    sb.append(this.getCoefficient());
                    sb.append("*");
                }
                if (!this.getVariables().isEmpty() && this.triFacs.isEmpty()) {
                    sb.append(printVars());
                } else if (this.getVariables().isEmpty() && !this.triFacs.isEmpty()) {
                    sb.append(printTris());
                } else {
                    sb.append(printVars());
                    sb.append("*");
                    sb.append(printTris());
                }
            }
        }
        return sb.toString();
    }

    public void checkEmptyVars() {
        if (this.getVariables().isEmpty()) {
            this.getIndexTable().clear();
        } else {
            int flag = 0;
            for (String string : this.getVariables()) {
                if (!string.equals("")) {
                    if (this.getIndexTable().get(string) > 0) {
                        flag = 1;
                        break;
                    }
                }
            }
            if (flag == 0) {
                this.getVariables().clear();
                this.getIndexTable().clear();
            }
        }
    }

    public String printVars() {
        StringBuilder sb = new StringBuilder();
        for (String string : this.getVariables()) {
            if (this.getIndexTable().get(string) == 0) {
                sb.append("");
            } else {
                if (this.getIndexTable().get(string) != 1) {
                    sb.append(string);
                    sb.append("**");
                    sb.append(this.getIndexTable().get(string));
                } else {
                    sb.append(string);
                }
            }
            sb.append("*");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String printTris() {
        StringBuilder sb = new StringBuilder();
        for (TriFac triFac : this.triFacs) {
            if (triFac.getIndex() == 0) {
                sb.append("");
            } else {
                if (triFac.getType() == 1) {
                    sb.append("sin((");
                } else {
                    sb.append("cos((");
                }
                sb.append(triFac.getExpPoly().toString());
                sb.append("))");
                if (triFac.getIndex() != 1) {
                    sb.append("**");
                    sb.append(triFac.getIndex());
                }
                sb.append("*");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
