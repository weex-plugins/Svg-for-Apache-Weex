package com.alibaba.weex.svg;

import android.graphics.Path;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by budao on 2017/3/1.
 */

public class SvgParser {

  private static final String TAG = "SvgParser";

  public static ArrayList<PathCmd> parserPath(String s) {
    ArrayList<PathCmd> pathCmds = new ArrayList<>();
    int n = s.length();
    ParserHelper ph = new ParserHelper(s, 0);
    ph.skipWhitespace();
    char prevCmd = 0;
    while (ph.pos < n) {
      char cmd = s.charAt(ph.pos);
      switch (cmd) {
        case '-':
        case '+':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          if (prevCmd == 'm' || prevCmd == 'M') {
            cmd = (char) ((prevCmd) - 1);
            break;
          } else if (("lhvcsqta").indexOf(Character.toLowerCase(prevCmd)) >= 0) {
            cmd = prevCmd;
            break;
          }
        default: {
          ph.advance();
          prevCmd = cmd;
        }
      }

      switch (cmd) {
        case 'M':
          pathCmds.add(new PathCmd("M", new float[]{ph.nextFloat(), ph.nextFloat()}));
          break;
        case 'm':
          pathCmds.add(new PathCmd("m", new float[]{ph.nextFloat(), ph.nextFloat()}));
          break;
        case 'Z':
          pathCmds.add(new PathCmd("Z", null));
          break;
        case 'z':
          pathCmds.add(new PathCmd("z", null));
          break;
        case 'T':
        case 't':
          // todo - smooth quadratic Bezier (two parameters)
        case 'L':
          pathCmds.add(new PathCmd("L", new float[]{ph.nextFloat(), ph.nextFloat()}));
          break;
        case 'l':
          pathCmds.add(new PathCmd("l", new float[]{ph.nextFloat(), ph.nextFloat()}));
          break;
        case 'H':
          pathCmds.add(new PathCmd("H", new float[]{ph.nextFloat()}));
          break;
        case 'h':
          pathCmds.add(new PathCmd("h", new float[]{ph.nextFloat()}));
          break;
        case 'V':
          pathCmds.add(new PathCmd("V", new float[]{ph.nextFloat()}));
          break;
        case 'v':
          pathCmds.add(new PathCmd("v", new float[]{ph.nextFloat()}));
          break;
        case 'C':
          pathCmds.add(new PathCmd("C", new float[]{
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat()
          }));
          break;
        case 'c':
          pathCmds.add(new PathCmd("c", new float[]{
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat()
          }));
          break;
        case 'Q':
        case 'q':
          // todo - quadratic Bezier (four parameters)
        case 'S':
          pathCmds.add(new PathCmd("S", new float[]{
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat()
          }));
          break;
        case 's':
          pathCmds.add(new PathCmd("s", new float[]{
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat()
          }));
          break;
        case 'A':
          pathCmds.add(new PathCmd("A", new float[]{
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFlag(),
              ph.nextFlag()
          }));
        case 'a':
          pathCmds.add(new PathCmd("a", new float[]{
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFloat(),
              ph.nextFlag(),
              ph.nextFlag()
          }));
        default:
          Log.w(TAG, "Invalid path command: " + cmd);
          ph.advance();
      }
      ph.skipWhitespace();
    }
    return pathCmds;
  }

  public static ArrayList<PathCmd> parsePoly(String s) {
    ArrayList<PathCmd> pathCmds = new ArrayList<>();
    int n = s.length();
    ParserHelper ph = new ParserHelper(s, 0);
    ph.skipWhitespace();
    pathCmds.add(new PathCmd("M", new float[]{ph.nextFloat(), ph.nextFloat()}));
    while (ph.pos < n) {
      pathCmds.add(new PathCmd("L", new float[]{ph.nextFloat(), ph.nextFloat()}));
    }
    pathCmds.add(new PathCmd("Z", null));
    return pathCmds;
  }

  public static ArrayList<PathCmd> parsePolyLine(String s) {
    ArrayList<PathCmd> pathCmds = new ArrayList<>();
    int n = s.length();
    ParserHelper ph = new ParserHelper(s, 0);
    ph.skipWhitespace();
    pathCmds.add(new PathCmd("M", new float[]{ph.nextFloat(), ph.nextFloat()}));
    while (ph.pos < n) {
      pathCmds.add(new PathCmd("L", new float[]{ph.nextFloat(), ph.nextFloat()}));
    }
    return pathCmds;
  }

  private static Path doPath(String s) {
    int n = s.length();
    ParserHelper ph = new ParserHelper(s, 0);
    ph.skipWhitespace();
    Path p = new Path();
    float lastX = 0;
    float lastY = 0;
    float lastX1 = 0;
    float lastY1 = 0;
    float subPathStartX = 0;
    float subPathStartY = 0;
    char prevCmd = 0;
    while (ph.pos < n) {
      char cmd = s.charAt(ph.pos);
      switch (cmd) {
        case '-':
        case '+':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          if (prevCmd == 'm' || prevCmd == 'M') {
            cmd = (char) ((prevCmd) - 1);
            break;
          } else if (("lhvcsqta").indexOf(Character.toLowerCase(prevCmd)) >= 0) {
            cmd = prevCmd;
            break;
          }
        default: {
          ph.advance();
          prevCmd = cmd;
        }
      }

      boolean wasCurve = false;
      switch (cmd) {
        case 'M':
        case 'm': {
          float x = ph.nextFloat();
          float y = ph.nextFloat();
          if (cmd == 'm') {
            subPathStartX += x;
            subPathStartY += y;
            p.rMoveTo(x, y);
            lastX += x;
            lastY += y;
          } else {
            subPathStartX = x;
            subPathStartY = y;
            p.moveTo(x, y);
            lastX = x;
            lastY = y;
          }
          break;
        }
        case 'Z':
        case 'z': {
          p.close();
          p.moveTo(subPathStartX, subPathStartY);
          lastX = subPathStartX;
          lastY = subPathStartY;
          lastX1 = subPathStartX;
          lastY1 = subPathStartY;
          wasCurve = true;
          break;
        }
        case 'T':
        case 't':
          // todo - smooth quadratic Bezier (two parameters)
        case 'L':
        case 'l': {
          float x = ph.nextFloat();
          float y = ph.nextFloat();
          if (cmd == 'l') {
            p.rLineTo(x, y);
            lastX += x;
            lastY += y;
          } else {
            p.lineTo(x, y);
            lastX = x;
            lastY = y;
          }
          break;
        }
        case 'H':
        case 'h': {
          float x = ph.nextFloat();
          if (cmd == 'h') {
            p.rLineTo(x, 0);
            lastX += x;
          } else {
            p.lineTo(x, lastY);
            lastX = x;
          }
          break;
        }
        case 'V':
        case 'v': {
          float y = ph.nextFloat();
          if (cmd == 'v') {
            p.rLineTo(0, y);
            lastY += y;
          } else {
            p.lineTo(lastX, y);
            lastY = y;
          }
          break;
        }
        case 'C':
        case 'c': {
          wasCurve = true;
          float x1 = ph.nextFloat();
          float y1 = ph.nextFloat();
          float x2 = ph.nextFloat();
          float y2 = ph.nextFloat();
          float x = ph.nextFloat();
          float y = ph.nextFloat();
          if (cmd == 'c') {
            x1 += lastX;
            x2 += lastX;
            x += lastX;
            y1 += lastY;
            y2 += lastY;
            y += lastY;
          }
          p.cubicTo(x1, y1, x2, y2, x, y);
          lastX1 = x2;
          lastY1 = y2;
          lastX = x;
          lastY = y;
          break;
        }
        case 'Q':
        case 'q':
          // todo - quadratic Bezier (four parameters)
        case 'S':
        case 's': {
          wasCurve = true;
          float x2 = ph.nextFloat();
          float y2 = ph.nextFloat();
          float x = ph.nextFloat();
          float y = ph.nextFloat();
          if (Character.isLowerCase(cmd)) {
            x2 += lastX;
            x += lastX;
            y2 += lastY;
            y += lastY;
          }
          float x1 = 2 * lastX - lastX1;
          float y1 = 2 * lastY - lastY1;
          p.cubicTo(x1, y1, x2, y2, x, y);
          lastX1 = x2;
          lastY1 = y2;
          lastX = x;
          lastY = y;
          break;
        }
        case 'A':
        case 'a': {
          float rx = ph.nextFloat();
          float ry = ph.nextFloat();
          float theta = ph.nextFloat();
          int largeArc = ph.nextFlag();
          int sweepArc = ph.nextFlag();
          float x = ph.nextFloat();
          float y = ph.nextFloat();
          if (cmd == 'a') {
            x += lastX;
            y += lastY;
          }
          //drawArc(p, lastX, lastY, x, y, rx, ry, theta, largeArc, sweepArc);
          lastX = x;
          lastY = y;
          break;
        }
        default:
          Log.w(TAG, "Invalid path command: " + cmd);
          ph.advance();
      }
      if (!wasCurve) {
        lastX1 = lastX;
        lastY1 = lastY;
      }
      ph.skipWhitespace();
    }
    return p;
  }

  public static class PathCmd {
    public String mCmd;
    public float[] mValue;

    public PathCmd(String cmd, float[] value) {
      mCmd = cmd;
      mValue = value;
    }

    @Override
    public String toString() {
      String desp = mCmd;
      if (mValue != null && mValue.length > 0) {
        for (float value : mValue) {
          desp += ", " + String.valueOf(value);
        }
      }

      return desp;
    }
  }
}
