/*
 * Decompiled with CFR 0.152.
 */
package gregtechmod.common;

import java.util.Random;

public class GT_IteratorRandom
extends Random {
    public int mIterationStep = Integer.MAX_VALUE;

    @Override
    public int nextInt(int aParameter) {
        if (this.mIterationStep == 0 || this.mIterationStep > aParameter) {
            this.mIterationStep = aParameter;
        }
        return --this.mIterationStep;
    }
}

