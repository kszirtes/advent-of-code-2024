package aoc2024.day03;

import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.List;

public class Day03 {
    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day03.txt");

        //System.out.println(lines.size());
        System.out.println(lines.stream().mapToLong(Day03::method1).sum());  //182619815

        System.out.println(lines.stream().mapToLong(Day03::method2).sum());  //85770822 nem jó

        StringBuilder sb = new StringBuilder();
        lines.forEach(sb::append);
        System.out.println(method2(sb.toString()));  //80747545


    }

    protected static long method1(String text) {
        String[] muls = text.split("mul");
        long multiply = 0;
        for (String mul : muls) {
            if (!mul.isEmpty() && mul.charAt(0) == '(') {
                String[] split1 = mul.split(",");
                if (split1.length >= 2) {
                    long a = 0, b = 0;
                    try {
                        a = Integer.parseInt(split1[0].substring(1));
                    } catch (NumberFormatException e) {
                        System.out.print("nan, ");
                    }

                    if (split1[1].length() >= 4 && split1[1].charAt(3) == ')' && split1[1].charAt(2) != ')' && split1[1].charAt(1) != ')') {
                        try {
                            b = Integer.parseInt(split1[1].substring(0, 3));
                        } catch (NumberFormatException e) {
                            System.out.print("nan, ");
                        }
                    } else if (split1[1].length() >= 3 && split1[1].charAt(2) == ')' && split1[1].charAt(1) != ')') {
                        try {
                            b = Integer.parseInt(split1[1].substring(0, 2));
                        } catch (NumberFormatException e) {
                            System.out.print("nan, ");
                        }
                    } else if (split1[1].length() >= 2 && split1[1].charAt(1) == ')') {
                        try {
                            b = Integer.parseInt(split1[1].substring(0, 1));
                        } catch (NumberFormatException e) {
                            System.out.print("nan, ");
                        }
                    }

                    if (a != 0 && b != 0) {
                        multiply = multiply + (a * b);
                    }
                }
            }
        }
        return multiply;
    }

    /*182619527
      182619815
       24650529
       32321083
       29286457
       31832172
       29722781
       34806505
    */

    protected static long method2(String text) {
        String[] muls = text.split("mul");
        long multiply = 0;
        boolean doIt = true;
        for (String mul : muls) {
            if (!mul.isEmpty()) {
                if (mul.charAt(0) == '(') {
                    String[] split1 = mul.split(",");
                    if (split1.length >= 2) {
                        long a = 0, b = 0;
                        try {
                            a = Integer.parseInt(split1[0].substring(1));
                        } catch (NumberFormatException e) {
                            System.out.print("nan, ");
                        }

                        if (split1[1].length() >= 4 && split1[1].charAt(3) == ')' && split1[1].charAt(2) != ')' && split1[1].charAt(1) != ')') {
                            try {
                                b = Integer.parseInt(split1[1].substring(0, 3));
                            } catch (NumberFormatException e) {
                                System.out.print("nan, ");
                            }
                        } else if (split1[1].length() >= 3 && split1[1].charAt(2) == ')' && split1[1].charAt(1) != ')') {
                            try {
                                b = Integer.parseInt(split1[1].substring(0, 2));
                            } catch (NumberFormatException e) {
                                System.out.print("nan, ");
                            }
                        } else if (split1[1].length() >= 2 && split1[1].charAt(1) == ')') {
                            try {
                                b = Integer.parseInt(split1[1].substring(0, 1));
                            } catch (NumberFormatException e) {
                                System.out.print("nan, ");
                            }
                        }

                        if (a != 0 && b != 0 && doIt) {
                            multiply = multiply + (a * b);
                        }
                    }
                }
                if (mul.contains("don't()")) {
                    doIt = false;
                }
                if (mul.contains("do()")) {
                    doIt = true;
                }
            }
        }
        return multiply;
    }
}
