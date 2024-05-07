public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double x = this.xxPos - p.xxPos;
        double y = this.yyPos - p.yyPos;
        return Math.sqrt(x * x + y * y);
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double r = this.calcDistance(p);
        return G * this.mass * p.mass / r / r;
    }

    public double calcForceExertedByX(Planet p) {
        double f = this.calcForceExertedBy(p);
        double distance = this.calcDistance(p);
        return f * (p.xxPos - this.xxPos) / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double f = this.calcForceExertedBy(p);
        double distance = this.calcDistance(p);
        return f * (p.yyPos - this.yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double result = 0;
        for (int i = 0; i < p.length; i++) {
            if (this.equals(p[i]))
                continue;
            else
                result += this.calcForceExertedByX(p[i]);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double result = 0;
        for (int i = 0; i < p.length; i++) {
            if (this.equals(p[i]))
                continue;
            else
                result += this.calcForceExertedByY(p[i]);
        }
        return result;
    }

    public void update(double dt, double xf, double yf) {
        double xa = xf / this.mass;
        double ya = yf / this.mass;
        this.xxVel += dt * xa;
        this.yyVel += dt * ya;
        this.yyPos += dt * this.yyVel;
        this.xxPos += dt * this.xxVel;
    }

    public void draw() {
        String img = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, img);
    }
}
