/*
 * Decompiled with CFR 0.152.
 */
package gregtechmod.common;

public class GT_ItsNotMyFaultException
extends RuntimeException {
    private String mError;

    public GT_ItsNotMyFaultException(String aError) {
        this.mError = aError;
    }

    @Override
    public String toString() {
        return "The GregTech-Addon has a Problem.\nIT'S NOT MY FAULT!!! Below is how to fix it.\n" + this.mError + "\nDO NOT COME TO ME WITH THIS CRASH. YOU CAUSED IT YOURSELF, AND I TOLD YOU HOW TO FIX IT!!!";
    }
}

