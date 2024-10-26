package org.project;

public class Automata {
    int cont;
    boolean aceptado;
    char[] car;

    public void iniciar(String cadena) {
        cont = 0;
        aceptado = false;
        car = cadena.toCharArray();
        q0();
    }

    public void q0() {
        System.out.println("En q0 - Inicio");
        if (cont < car.length) {
            char actual = car[cont];
            if (actual == 'e' && car[cont + 1] == 'c') {
                cont += 2;
                qEnteroCorto();
            } else if (actual == 'i' && car[cont + 1] == 'd') {
                cont += 2;
                qIdentificador();
            } else if (actual == '(' || actual == ')') {
                cont++;
                qParentesis();
            } else if (actual == '0' && (cont + 1 < car.length && car[cont + 1] == 'x')) {
                cont += 2;
                qHexadecimal();
            } else if (actual == '=' || actual == '!' || actual == '<' || actual == '>') {
                cont++;
                qOperadorRelacional();
            }else if (actual == '\"' || actual == '\'') {
                cont++;
                qCadenaCaracteres(actual);
            }
            else if (actual == '\"') {
                cont++;
                qCadenaCaracteres();
            } else if (actual == 'Q') {
                cont++;
                qDinero();
            } else if (actual == 'c' && (cont + 1 < car.length && car[cont + 1] == 'l')) {
                cont += 2; // Avanza el prefijo 'cl'
                qHora();
            } else if (actual == '+') {
                cont++;
                qOperadorAditivo();
            } else if (actual == '-') {
                cont++;
                qOperadorAditivo();
            } else if (actual == '*') {
                cont++;
                qOperadorMultiplicativo();
            } else if (actual == 'f') {
                cont++;
                qFecha();
            } else if (actual == 'n' && car[cont + 1] == 'r') {
                cont += 2;
                qNumeroReal();
            } else if (actual == 'n' && car[cont + 1] == 'c') {
                cont += 2;
                qNumeroRealCientifico();
            } else if (actual == '&' || actual == '|' || actual == '!') {
                cont++;
                qOperadorLogico();
            } else {
                System.out.println("Token no reconocido.");
            }
        }
    }

    public void qEnteroCorto() {
        while (cont < car.length && Character.isDigit(car[cont])) {
            cont++;
        }
        aceptado = true;
        System.out.println("Token reconocido: Entero Corto");
    }

    public void qIdentificador() {
        while (cont < car.length && (Character.isLetterOrDigit(car[cont]) || car[cont] == '_')) {
            cont++;
        }
        aceptado = true;
        System.out.println("Token reconocido: Identificador");
    }

    public void qParentesis() {
        aceptado = true;
        System.out.println("Token reconocido: Paréntesis");
    }

    public void qHexadecimal() {
        while (cont < car.length && Character.digit(car[cont], 16) != -1) {
            cont++;
        }
        aceptado = true;
        System.out.println("Token reconocido: Hexadecimal");
    }
    public void qCadenaCaracteres(char delimitador) {
        while (cont < car.length && car[cont] != delimitador) {
            if (car[cont] == '\"' || car[cont] == '\'') {
                break; // Termina si encuentra un delimitador
            }
            cont++;
        }
        if (cont < car.length && car[cont] == delimitador) {
            cont++;
            aceptado = true;
            System.out.println("Token reconocido: Cadena de Caracteres");
        } else {
            System.out.println("Token no reconocido: cadena incompleta");
        }
    }
    public void qOperadorRelacional() {
        if (cont < car.length && (car[cont] == '=' || car[cont] == '<' || car[cont] == '>')) {
            cont++;
        }
        aceptado = true;
        System.out.println("Token reconocido: Operador Relacional");
    }

    public void qCadenaCaracteres() {
        while (cont < car.length && car[cont] != '\"') {
            cont++;
        }
        if (cont < car.length && car[cont] == '\"') {
            cont++;
            aceptado = true;
            System.out.println("Token reconocido: Cadena de Caracteres");
        }
    }

    public void qDinero() {
        while (cont < car.length && Character.isDigit(car[cont])) {
            cont++;
        }
        if (cont < car.length && car[cont] == '.') {
            cont++;
            while (cont < car.length && Character.isDigit(car[cont])) {
                cont++;
            }
        }
        aceptado = true;
        System.out.println("Token reconocido: Dinero");
    }

    public void qHora() {
        if (cont < car.length && car[cont] == 'l') {
            cont++;
            if (cont < car.length && Character.isDigit(car[cont])) {
                cont++;
                if (cont < car.length && Character.isDigit(car[cont])) {
                    cont++;
                    if (cont < car.length && car[cont] == ':') {
                        cont++;
                        if (cont < car.length && Character.isDigit(car[cont])) {
                            cont++;
                            if (cont < car.length && Character.isDigit(car[cont])) {
                                cont++;
                                aceptado = true;
                                System.out.println("Token reconocido: Hora");
                            } else {
                                System.out.println("Token no reconocido: formato de hora incorrecto");
                            }
                        } else {
                            System.out.println("Token no reconocido: formato de hora incorrecto");
                        }
                    } else {
                        System.out.println("Token no reconocido: formato de hora incorrecto");
                    }
                } else {
                    System.out.println("Token no reconocido: formato de hora incorrecto");
                }
            } else {
                System.out.println("Token no reconocido: formato de hora incorrecto");
            }
        }
    }


    public void qOperadorAditivo() {
        aceptado = true;
        System.out.println("Token reconocido: Operador Aditivo");
    }

    public void qOperadorMultiplicativo() {
        aceptado = true;
        System.out.println("Token reconocido: Operador Multiplicativo");
    }

    public void qFecha() {
        while (cont < car.length && Character.isDigit(car[cont])) {
            cont++;
        }
        if (cont < car.length && car[cont] == '/') {
            cont++;
            while (cont < car.length && Character.isDigit(car[cont])) {
                cont++;
            }
        }
        aceptado = true;
        System.out.println("Token reconocido: Fecha");
    }

    public void qNumeroReal() {
        if (cont < car.length && (car[cont] == '+' || car[cont] == '-')) {
            cont++;
        }
        while (cont < car.length && Character.isDigit(car[cont])) {
            cont++;
        }
        if (cont < car.length && car[cont] == '.') {
            cont++;
            while (cont < car.length && Character.isDigit(car[cont])) {
                cont++;
            }
        }
        aceptado = true;
        System.out.println("Token reconocido: Número Real");
    }

    public void qNumeroRealCientifico() {
        if (cont < car.length && (car[cont] == '+' || car[cont] == '-')) {
            cont++;
        }
        while (cont < car.length && Character.isDigit(car[cont])) {
            cont++;
        }
        if (cont < car.length && (car[cont] == 'e' || car[cont] == 'E')) {
            cont++;
            if (cont < car.length && (car[cont] == '+' || car[cont] == '-')) {
                cont++;
            }
            while (cont < car.length && Character.isDigit(car[cont])) {
                cont++;
            }
        }
        aceptado = true;
        System.out.println("Token reconocido: Número Real en Notación Científica");
    }

    public void qOperadorLogico() {
        if (cont < car.length && (car[cont] == '&' || car[cont] == '|')) {
            cont++;
            aceptado = true;
            System.out.println("Token reconocido: Operador Lógico");
        } else if (car[cont] == '!') {
            cont++;
            aceptado = true;
            System.out.println("Token reconocido: Operador Lógico");
        }
    }
}
