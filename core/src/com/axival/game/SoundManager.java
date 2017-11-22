package com.axival.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import javax.smartcardio.Card;

public class SoundManager {
    //define variable
    private Music musicMenu, musicCharacter, musicGameplayNormal, musicGameplayCritical, musicLoading, musicVictory, musicDefeate;
    private Music fxClickCard, fxSelectCharacter, fxSelected;
    private boolean status1=true, status2=true;
    private CardPlay cardPlay;

    private Music cardArcane, cardArmor, cardGuardian, cardHeaven, cardPotion, cardThunder, cardWeapon;
    private Music dtNormal, dtUlti, wNormal, wIce, wHurricane, pMercy, pClean, pKarma;

    private Music dieHero;

    public SoundManager(CardPlay cardPlay){
        //set Music assets in variable
        this.cardPlay = cardPlay;
        this.musicMenu =  Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/1.Menu bgm.ogg"));
        this.musicCharacter = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/2.Character select - bgm (20sec).ogg"));
        this.musicGameplayNormal = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/3.Gameplay bgm(Witcher3).ogg"));
        this.musicGameplayCritical = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/4.Gameplay - Critical sound.ogg"));
        this.fxClickCard = Gdx.audio.newMusic(Gdx.files.internal("sound/fx/Game play - click card.ogg")); //main click
        this.fxSelectCharacter = Gdx.audio.newMusic(Gdx.files.internal("sound/fx/select character - click card.ogg")); //select card
        this.fxSelected = Gdx.audio.newMusic(Gdx.files.internal("sound/fx/Select character - selected.ogg")); //count down
        this.musicVictory = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/6.Victory BGM.ogg"));
        this.musicDefeate = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/5.Defeated - Ash - The Secession Studios (mp3cut.net).ogg"));
        this.musicLoading = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/Original - UI (mp3cut.net).ogg"));

        //sound card sfx
        this.cardArcane = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/arcane shield.ogg"));
        this.cardArmor = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/Arnor++.ogg"));
        this.cardGuardian = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/Guardian angel.ogg"));
        this.cardHeaven = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/Heaven elixir.ogg"));
        this.cardPotion = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/Potion.ogg"));
        this.cardThunder = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/Thunderbolt.ogg"));
        this.cardWeapon = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/Weapon+.ogg"));

        //sound hero sfx
        this.dtNormal = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/skill/DT normal att_Counter sfx.ogg"));
        this.dtUlti = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/skill/DT_Ultimate.ogg"));
        this.wNormal = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/skill/W_normal att.ogg"));
        this.wIce = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/skill/W_ice meteor.ogg"));
        this.wHurricane = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/skill/W_Hurricane.ogg"));
        this.pMercy = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/skill/P_Mercy.ogg"));
        this.pClean = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/skill/P_Cleansing light.ogg"));
        this.pKarma = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/skill/P_Karma Backfire.ogg"));
        this.dieHero = Gdx.audio.newMusic(Gdx.files.internal("sound/Skill Sound FX/skill/Die sfx.ogg"));

        //set volume music
        musicMenu.setVolume(.2f);
        musicCharacter.setVolume(.2f);
        musicGameplayNormal.setVolume(.2f);
        musicGameplayCritical.setVolume(.2f);
        musicLoading.setVolume(6f);
    }

    //play select Bgm
    public void playBgm(int statusSound){
        //status sound => 0=menu, 1=character, 2=gameplay1, 3=gameplay2, 4=loading, 5=victory, 6=defeat
        if(false) {
            if (statusSound == 0) {
                musicMenu.play();
                musicMenu.setLooping(true);
            } else if (statusSound == 1) {
                musicCharacter.play();
                musicCharacter.setLooping(true);
            } else if (statusSound == 2) {
                musicGameplayNormal.play();
                musicGameplayNormal.setLooping(true);
            } else if (statusSound == 3) {
                musicGameplayCritical.play();
                musicGameplayCritical.setLooping(true);
            } else if (statusSound == 4) {
                musicLoading.play();
                musicLoading.setLooping(true);
            } else if (statusSound == 5) {
                musicVictory.play();
                //musicVictory.setLooping(true);
            } else if (statusSound == 6) {
                musicDefeate.play();
                //musicDefeate.setLooping(true);
            }
        }
    }

    //pause select Bgm
    public void pauseBgm(int statusSound){
        //status sound => 0=menu, 1=character, 2=gameplay1, 3=gameplay2, 4=loading, 5=victory, 6=defeat
        if(statusSound==0) {
            musicMenu.pause();
        }
        else if(statusSound==1) {
            musicCharacter.pause();
        }
        else if(statusSound==2) {
            musicGameplayNormal.pause();
        }
        else if(statusSound==3) {
            musicGameplayCritical.pause();
        }
        else if(statusSound==4) {
            musicLoading.pause();
        }
        else if(statusSound==5) {
            musicVictory.pause();
        }
        else if(statusSound==6) {
            musicDefeate.pause();
        }
    }

    //stop select Bgm
    public void stopBgm(int statusSound){
        //status sound => 0=menu, 1=character, 2=gameplay1, 3=gameplay2, 4=loading, 5=victory, 6=defeat
        if(statusSound==0) {
            musicMenu.stop();
        }
        else if(statusSound==1) {
            musicCharacter.stop();
        }
        else if(statusSound==2) {
            musicGameplayNormal.stop();
        }
        else if(statusSound==3) {
            musicGameplayCritical.stop();
        }
        else if(statusSound==4) {
            musicLoading.stop();
        }
        else if(statusSound==5) {
            musicVictory.stop();
        }
        else if(statusSound==6) {
            musicDefeate.stop();
        }
    }

    //stop all Bgm and set Status Bgm to off
    public void stopBgmAll(boolean check){
        if(check){
            musicMenu.stop();
            musicCharacter.stop();
            musicGameplayNormal.stop();
            musicGameplayCritical.stop();
            musicLoading.stop();
            musicVictory.stop();
            musicDefeate.stop();
            status1 = false;
        }
        else {
            status1 = true;
        }
    }

    //play select sfx
    public void playSfx(int statusSound){
        // 0= clickCardMain, 1=select, 2=selected, >2 = cardSkillSfx, 9> = skillHero
        if(status2) {
            if (statusSound == 0) {
                fxClickCard.play();
            } else if (statusSound == 1) {
                fxSelectCharacter.play();
            } else if (statusSound == 2) {
                fxSelected.play();
            } else if (statusSound == 3) {//card skill
                cardPotion.play();
            } else if (statusSound == 4) {
                cardHeaven.play();
            } else if (statusSound == 5) {
                cardArcane.play();
            } else if (statusSound == 6) {
                cardThunder.play();
            } else if (statusSound == 7) {
                cardGuardian.play();
            } else if (statusSound == 8) {
                cardWeapon.play();
            } else if (statusSound == 9) {
                cardArmor.play();
            } else if (statusSound == 10) {//hero skill
                dtNormal.play();
            } else if (statusSound == 11) {
                dtUlti.play();
            } else if (statusSound == 12) {
                wNormal.play();
            } else if (statusSound == 13) {
                wIce.play();
            } else if (statusSound == 14) {
                wHurricane.play();
            } else if (statusSound == 15) {
                pMercy.play();
            } else if (statusSound == 16) {
                pClean.play();
            } else if (statusSound == 17) {
                pKarma.play();
            }
            else if (statusSound == 18){
                dieHero.play();
            }

        }
    }

    //pause select sfx
    public void pauseSfx(int statusSound){
        if(statusSound==0) {
            fxClickCard.pause();
        }
        else if(statusSound==1) {
            fxSelectCharacter.pause();
        }
        else if(statusSound==2) {
            fxSelected.pause();
        }
        else if (statusSound == 3) {//card skill
            cardPotion.pause();
        } else if (statusSound == 4) {
            cardHeaven.pause();
        } else if (statusSound == 5) {
            cardArcane.pause();
        } else if (statusSound == 6) {
            cardThunder.pause();
        } else if (statusSound == 7) {
            cardGuardian.pause();
        } else if (statusSound == 8) {
            cardWeapon.pause();
        } else if (statusSound == 9) {
            cardArmor.pause();
        } else if (statusSound == 10) {//hero skill
            dtNormal.pause();
        } else if (statusSound == 11) {
            dtUlti.pause();
        } else if (statusSound == 12) {
            wNormal.pause();
        } else if (statusSound == 13) {
            wIce.pause();
        } else if (statusSound == 14) {
            wHurricane.pause();
        } else if (statusSound == 15) {
            pMercy.pause();
        } else if (statusSound == 16) {
            pClean.pause();
        } else if (statusSound == 17) {
            pKarma.pause();
        }
        else if (statusSound == 18){
            dieHero.pause();
        }
    }

    //stop select sfx
    public void stopSfx(int statusSound){
        if(statusSound==0) {
            fxClickCard.stop();
        }
        else if(statusSound==1) {
            fxSelectCharacter.stop();
        }
        else if(statusSound==2) {
            fxSelected.stop();
        }else if (statusSound == 3) {//card skill
            cardPotion.stop();
        } else if (statusSound == 4) {
            cardHeaven.stop();
        } else if (statusSound == 5) {
            cardArcane.stop();
        } else if (statusSound == 6) {
            cardThunder.stop();
        } else if (statusSound == 7) {
            cardGuardian.stop();
        } else if (statusSound == 8) {
            cardWeapon.stop();
        } else if (statusSound == 9) {
            cardArmor.stop();
        } else if (statusSound == 10) {//hero skill
            dtNormal.stop();
        } else if (statusSound == 11) {
            dtUlti.stop();
        } else if (statusSound == 12) {
            wNormal.stop();
        } else if (statusSound == 13) {
            wIce.stop();
        } else if (statusSound == 14) {
            wHurricane.stop();
        } else if (statusSound == 15) {
            pMercy.stop();
        } else if (statusSound == 16) {
            pClean.stop();
        } else if (statusSound == 17) {
            pKarma.stop();
        }
        else if (statusSound == 18){
            dieHero.stop();
        }
    }

    //stop all sfx and set status sfx to off
    public void stopSfxAll(boolean check){
        if(check) {
            fxClickCard.stop();
            fxSelectCharacter.stop();
            fxSelected.stop();

            cardPotion.stop();
            cardHeaven.stop();
            cardArcane.stop();
            cardThunder.stop();
            cardGuardian.stop();
            cardWeapon.stop();
            cardArmor.stop();

            dtNormal.stop();
            dtUlti.stop();
            wNormal.stop();
            wIce.stop();
            wHurricane.stop();
            pMercy.stop();
            pClean.stop();
            pKarma.stop();

            dieHero.stop();

            status2 = false;
        }
        else {
            status2 = true;
        }
    }

    //check bgm from select
    public boolean checkMusicStatusBgm(int statusSound){
        if(statusSound==0) {
            return musicMenu.isPlaying();
        }
        else if(statusSound==1) {
            return musicCharacter.isPlaying();
        }
        else if(statusSound==2) {
            return musicGameplayNormal.isPlaying();
        }
        else if(statusSound==3) {
            return musicGameplayCritical.isPlaying();
        }
        return false;
    }

    //check sfx from select
    public boolean checkMusicStatusSfx(int statusSound){
        if(statusSound==0) {
            return fxClickCard.isPlaying();
        }
        else if(statusSound==1) {
            return fxSelectCharacter.isPlaying();
        }
        else if(statusSound==2) {
            return fxSelected.isPlaying();
        }
        return false;
    }

    //check status sfx all
    public boolean checkMusicStatusSfxAll(){
        return status2;
    }

    //check status bgm all
    public boolean checkMusicStatusBgmAll(){
        return status1;
    }
}
