package nightgames.stance;

import nightgames.characters.Character;

public class Mount extends AbstractFacingStance {

    public Mount(Character top, Character bottom) {
        super(top, bottom, Stance.mount);
    }

    @Override
    public String describe() {
        if (top.human()) {
            return "You're on top of " + bottom.name() + ".";
        } else {
            return top.name() + " is straddling you, with her enticing breasts right in front of you.";
        }
    }

    @Override
    public String image() {
        if (top.hasPussy() && bottom.hasPussy()) {
            return "mount_ff.jpg";
        } else if (bottom.hasPussy()) {
            return "mount_m.jpg";
        } else {
            return "mount_f.jpg";
        }
    }

    @Override
    public boolean mobile(Character c) {
        return c == top;
    }

    @Override
    public boolean kiss(Character c) {
        return true;
    }

    @Override
    public boolean dom(Character c) {
        return c == top;
    }

    @Override
    public boolean sub(Character c) {
        return c == bottom;
    }

    @Override
    public boolean reachTop(Character c) {
        return true;
    }

    @Override
    public boolean reachBottom(Character c) {
        return c == top;
    }

    @Override
    public boolean prone(Character c) {
        return c == bottom;
    }

    @Override
    public boolean feet(Character c) {
        return false;
    }

    @Override
    public boolean oral(Character c) {
        return false;
    }

    @Override
    public boolean behind(Character c) {
        return false;
    }

    @Override
    public boolean inserted(Character c) {
        return false;
    }

    @Override
    public float priorityMod(Character self) {
        return getSubDomBonus(self, 4.0f);
    }

    @Override
    public double pheromoneMod(Character self) {
        return 3;
    }
    
    @Override
    public int dominance() {
        return 2;
    }
}
