package nightgames.skills;

import nightgames.characters.Attribute;
import nightgames.characters.Character;
import nightgames.characters.Emotion;
import nightgames.characters.Trait;
import nightgames.characters.body.BreastsPart;
import nightgames.combat.Combat;
import nightgames.combat.Result;
import nightgames.global.Global;
import nightgames.stance.NursingHold;
import nightgames.stance.Stance;
import nightgames.status.BodyFetish;
import nightgames.status.Stsflag;
import nightgames.status.Suckling;

public class Nurse extends Skill {

    public Nurse(Character self) {
        super("Nurse", self);

    }

    @Override
    public boolean requirements(Combat c, Character user, Character target) {
        return getSelf().get(Attribute.Seduction) > 10;
    }

    @Override
    public boolean usable(Combat c, Character target) {
        return getSelf().breastsAvailable() && c.getStance().reachTop(getSelf()) && c.getStance().front(getSelf())
                        && getSelf().body.getLargestBreasts().size >= BreastsPart.c.size
                        && c.getStance().mobile(getSelf())
                        && (!c.getStance().mobile(target) || c.getStance().prone(target)) && getSelf().canAct();
    }

    @Override
    public float priorityMod(Combat c) {
        int mod = getSelf().has(Trait.lactating) ? 3 : 0;
        if (getSelf().has(Trait.magicmilk))
            mod *= 3;
        return mod;
    }

    @Override
    public boolean resolve(Combat c, Character target) {
        if (getSelf().human()) {
            c.write(getSelf(), deal(c, 0, Result.normal, target));
        } else if (target.human()) {
            c.write(getSelf(), receive(c, 0, Result.normal, target));
        }
        if (getSelf().has(Trait.lactating) && !target.is(Stsflag.suckling) && !target.is(Stsflag.wary)) {
            c.write(target, Global.format(
                            "{other:SUBJECT-ACTION:are|is} a little confused at the sudden turn of events, but after milk starts flowing into {other:possessive} mouth, {other:pronoun} can't help but continue to suck on {self:possessive} teats.",
                            getSelf(), target));
            target.add(c, new Suckling(target, getSelf(), 4));
        }
        if (c.getStance().en != Stance.nursing && !c.getStance().havingSex()) {
            c.setStance(new NursingHold(getSelf(), target));
            getSelf().emote(Emotion.dominant, 20);
        } else {
            new Suckle(target).resolve(c, getSelf());
            getSelf().emote(Emotion.dominant, 10);
        }
        if (Global.random(100) < 5 + 2 * getSelf().get(Attribute.Fetish)) {
            target.add(c, new BodyFetish(target, getSelf(), BreastsPart.a.getType(), .25));
        }
        return true;
    }

    @Override
    public int getMojoCost(Combat c) {
        if (c.getStance().en != Stance.nursing) {
            return 20;
        } else {
            return 0;
        }
    }

    @Override
    public int getMojoBuilt(Combat c) {
        if (c.getStance().en != Stance.nursing) {
            return 0;
        } else {
            return 10;
        }
    }

    @Override
    public Skill copy(Character user) {
        return new Nurse(user);
    }

    @Override
    public int speed() {
        return 6;
    }

    @Override
    public Tactics type(Combat c) {
        if (c.getStance().enumerate() != Stance.nursing) {
            return Tactics.positioning;
        } else {
            return Tactics.pleasure;
        }
    }

    @Override
    public String deal(Combat c, int damage, Result modifier, Character target) {
        if (modifier == Result.special) {
            return "You cradle " + target.name() + "'s head in your lap and press your "
                            + getSelf().body.getRandomBreasts().fullDescribe(getSelf()) + " over her face. "
                            + target.name()
                            + " vocalizes a confused little yelp, and you take advantage of it to force your nipples between her lips.";
        } else {
            return "You gently stroke " + target.nameOrPossessivePronoun() + " hair as you feed your nipples to "
                            + target.directObject() + ". " + "Even though she is reluctant at first, you soon have "
                            + target.name() + " sucking your teats like a newborn.";
        }
    }

    @Override
    public String receive(Combat c, int damage, Result modifier, Character target) {
        if (modifier == Result.special) {
            return getSelf().name() + " plops her " + getSelf().body.getRandomBreasts().fullDescribe(getSelf())
                            + " in front of your face. You vision suddenly consists of only swaying titflesh."
                            + " Giggling a bit, " + getSelf().name()
                            + " pokes your sides and slides her nipples in your mouth when you let out a yelp.";
        } else {
            return getSelf().name() + " gently strokes your hair as she presents her nipples to your mouth. "
                            + "Presented with the opportunity, you happily suck on her breasts.";
        }
    }

    @Override
    public String describe(Combat c) {
        return "Feed your nipples to your opponent";
    }

    @Override
    public boolean makesContact() {
        return true;
    }
}
