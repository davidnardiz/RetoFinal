package tienda;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Es una clase auxiliar que genera campos personalizados.
 *
 * @author Bayron
 */
public class Star2D implements Shape {

    private Shape starShape;
    private double x;
    private double y;
    private double innerRadius;
    private double outerRadius;
    private int branchesCount;

    public Star2D(double x, double y,
            double innerRadius, double outerRadius,
            int branchesCount) {
        if (branchesCount < 3) {
            throw new IllegalArgumentException("The number of branches must"
                    + " be >= 3.");
        } else if (innerRadius >= outerRadius) {
            throw new IllegalArgumentException("The inner radius must be < "
                    + "outer radius.");
        }

        this.x = x;
        this.y = y;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.branchesCount = branchesCount;

        starShape = generateStar(x, y, innerRadius, outerRadius, branchesCount);
    }

    private static Shape generateStar(double x, double y,
            double innerRadius, double outerRadius,
            int branchesCount) {
        GeneralPath path = new GeneralPath();

        double outerAngleIncrement = 2 * Math.PI / branchesCount;

        double outerAngle = branchesCount % 2 == 0 ? 0.0 : -(Math.PI / 2.0);
        double innerAngle = (outerAngleIncrement / 2.0) + outerAngle;

        float x1 = (float) (Math.cos(outerAngle) * outerRadius + x);
        float y1 = (float) (Math.sin(outerAngle) * outerRadius + y);

        float x2 = (float) (Math.cos(innerAngle) * innerRadius + x);
        float y2 = (float) (Math.sin(innerAngle) * innerRadius + y);

        path.moveTo(x1, y1);
        path.lineTo(x2, y2);

        outerAngle += outerAngleIncrement;
        innerAngle += outerAngleIncrement;

        for (int i = 1; i < branchesCount; i++) {
            x1 = (float) (Math.cos(outerAngle) * outerRadius + x);
            y1 = (float) (Math.sin(outerAngle) * outerRadius + y);

            path.lineTo(x1, y1);

            x2 = (float) (Math.cos(innerAngle) * innerRadius + x);
            y2 = (float) (Math.sin(innerAngle) * innerRadius + y);

            path.lineTo(x2, y2);

            outerAngle += outerAngleIncrement;
            innerAngle += outerAngleIncrement;
        }

        path.closePath();
        return path;
    }

    public void setInnerRadius(double innerRadius) {
        if (innerRadius >= outerRadius) {
            throw new IllegalArgumentException("The inner radius must be <"
                    + " outer radius.");
        }

        this.innerRadius = innerRadius;
        starShape = generateStar(getX(), getY(), innerRadius, getOuterRadius(),
                getBranchesCount());
    }

    public void setX(double x) {
        this.x = x;
        starShape = generateStar(x, getY(), getInnerRadius(), getOuterRadius(),
                getBranchesCount());
    }

    public void setY(double y) {
        this.y = y;
        starShape = generateStar(getX(), y, getInnerRadius(), getOuterRadius(),
                getBranchesCount());
    }

    public void setOuterRadius(double outerRadius) {
        if (innerRadius >= outerRadius) {
            throw new IllegalArgumentException("The outer radius must be > "
                    + "inner radius.");
        }

        this.outerRadius = outerRadius;
        starShape = generateStar(getX(), getY(), getInnerRadius(), outerRadius,
                getBranchesCount());
    }

    public void setBranchesCount(int branchesCount) {
        if (branchesCount <= 2) {
            throw new IllegalArgumentException("The number of branches must"
                    + " be >= 3.");
        }

        this.branchesCount = branchesCount;
        starShape = generateStar(getX(), getY(), getInnerRadius(),
                getOuterRadius(), branchesCount);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getInnerRadius() {
        return innerRadius;
    }

    public double getOuterRadius() {
        return outerRadius;
    }

    public int getBranchesCount() {
        return branchesCount;
    }

    @Override
    public Rectangle getBounds() {
        return starShape.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return starShape.getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
        return starShape.contains(x, y);
    }

    @Override
    public boolean contains(Point2D p) {
        return starShape.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return starShape.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return starShape.intersects(r);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return starShape.contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return starShape.contains(r);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return starShape.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return starShape.getPathIterator(at, flatness);
    }
}
