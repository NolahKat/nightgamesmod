package nightgames.characters;

import nightgames.combat.Combat;
import nightgames.combat.Result;

import java.util.Optional;

/**
 * Test personality, or maybe for those with a flat affect.
 */
public class BlankPersonality extends BasePersonality {
    public BlankPersonality(String name, int level) {
        super(name, level, Optional.empty(), Optional.empty());
    }

    @Override public String bbLiner(Combat c) {
        return null;
    }

    @Override public String nakedLiner(Combat c) {
        return null;
    }

    @Override public String stunLiner(Combat c) {
        return null;
    }

    @Override public String taunt(Combat c) {
        return null;
    }

    @Override public String victory(Combat c, Result flag) {
        return null;
    }

    @Override public String defeat(Combat c, Result flag) {
        return null;
    }

    @Override public String victory3p(Combat c, Character target, Character assist) {
        return null;
    }

    @Override public String intervene3p(Combat c, Character target, Character assist) {
        return null;
    }

    @Override public String describe(Combat c) {
        return "test 2 3";
    }

    @Override public String draw(Combat c, Result flag) {
        return null;
    }

    @Override public boolean fightFlight(Character opponent) {
        return false;
    }

    @Override public boolean attack(Character opponent) {
        return false;
    }

    @Override public String startBattle(Character other) {
        return null;
    }

    @Override public boolean fit() {
        return false;
    }

    @Override public String night() {
        return null;
    }

    @Override public boolean checkMood(Combat c, Emotion mood, int value) {
        return value >= 100;
    }

    @Override public String temptLiner(Combat c) {
        return null;
    }

    @Override public String orgasmLiner(Combat c) {
        return null;
    }

    @Override public String makeOrgasmLiner(Combat c) {
        return null;
    }

    @Override protected void applyBasicStats() {

    }

    @Override public void setGrowth() {

    }
}
