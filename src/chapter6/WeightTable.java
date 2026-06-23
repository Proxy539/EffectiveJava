import chapter6.Planet;

void main(String[] args) {
    double earthWeight = Double.parseDouble(args[0]);
    double mass = earthWeight / Planet.EARTH.surfaceGravity();
    for (Planet p : Planet.values()) {
        System.out.printf("Weight on planet %s equals %f%n", p, p.surfaceWeight(mass));
    }
}
