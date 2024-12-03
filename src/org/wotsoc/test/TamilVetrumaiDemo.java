/**
 * @author Rajamani David
 * @since	May 15, 2019
 *
 */
package org.wotsoc.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.wotsoc.illakanam.TamilVetrumai;

/**
 * @author rdavid
 *
 */
public class TamilVetrumaiDemo {

	@Test
	public void testCase1()
	{
		/**
		 * Positive Cases
		 * */
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("புலமையை")==true);
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("புகழை")==true);
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("மாநிலத்தை")==true);
		assertTrue(TamilVetrumai.மூன்றாம்வேற்றுமையா("அரிவாளால்")==true);
		assertTrue(TamilVetrumai.மூன்றாம்வேற்றுமையா("தாயொடு")==true);
		assertTrue(TamilVetrumai.நான்காம்வேற்றுமையா("பொன்னுக்குப்")==true);
		assertTrue(TamilVetrumai.நான்காம்வேற்றுமையா("பொன்னுக்கு")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("மலையின்")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("செல்வத்தில்")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("வீட்டிலிருந்து")==true);
		assertTrue(TamilVetrumai.ஆறாம்வேற்றுமையா("என")==true);
		assertTrue(TamilVetrumai.ஆறாம்வேற்றுமையா("எனது")==true);
		assertTrue(TamilVetrumai.ஏழாம்வேற்றுமையா("அரைநொடியில்")==true);
		assertTrue(TamilVetrumai.ஏழாம்வேற்றுமையா("வேனிற்கண்")==true);
		/**
		 * Negative Cases
		 * */
		assertFalse(TamilVetrumai.இரண்டாம்வேற்றுமையா("சாப்பாட்டு")==true);
		assertFalse(TamilVetrumai.மூன்றாம்வேற்றுமையா("தாயொடும்")==true);
		assertFalse(TamilVetrumai.நான்காம்வேற்றுமையா("பொன்னன்")==true);
		assertFalse(TamilVetrumai.ஐந்தாம்வேற்றுமையா("காட்டினி")==true);	
		assertFalse(TamilVetrumai.ஆறாம்வேற்றுமையா("எனாதும்")==true);
		assertFalse(TamilVetrumai.ஏழாம்வேற்றுமையா("வேனிற்கண்ணை")==true);

	}
	
	@Test
	public void testCase2()
	{
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("புலமையை")==true);
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("புகழை")==true);
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("பாடலை")==true);                        
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("இரயிலை")==true);                        
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("சாப்பாட்டை")==true);                        
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("மனவியை")==true);                        
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("மாநிலத்தை")==true);
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("கண்ணை")==true);
		assertTrue(TamilVetrumai.இரண்டாம்வேற்றுமையா("வழக்கை")==true);
		assertTrue(TamilVetrumai.மூன்றாம்வேற்றுமையா("அரிவாளால்")==true);
		assertTrue(TamilVetrumai.மூன்றாம்வேற்றுமையா("அறத்தான்")==true);
		assertTrue(TamilVetrumai.மூன்றாம்வேற்றுமையா("வள்ளியோடு")==true);
		assertTrue(TamilVetrumai.மூன்றாம்வேற்றுமையா("தாயொடு")==true);
		assertTrue(TamilVetrumai.நான்காம்வேற்றுமையா("பொன்னுக்குப்")==true);
		assertTrue(TamilVetrumai.நான்காம்வேற்றுமையா("பொன்னுக்கு")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("மலையின்")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("பாலின்")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("கல்வியில்")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("செல்வத்தில்")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("வீட்டிலிருந்து")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("ஊரிலிருந்து")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("ஊரினின்று")==true);
		assertTrue(TamilVetrumai.ஐந்தாம்வேற்றுமையா("காட்டினின்று")==true);
		assertTrue(TamilVetrumai.ஆறாம்வேற்றுமையா("என")==true);
		assertTrue(TamilVetrumai.ஆறாம்வேற்றுமையா("எனது")==true);
		assertTrue(TamilVetrumai.ஆறாம்வேற்றுமையா("எனாது")==true);
		assertTrue(TamilVetrumai.ஏழாம்வேற்றுமையா("அரைநொடியில்")==true);
		assertTrue(TamilVetrumai.ஏழாம்வேற்றுமையா("வேனிற்கண்")==true);
		
		assertFalse(TamilVetrumai.இரண்டாம்வேற்றுமையா("சாப்பாட்டு")==true);
		assertFalse(TamilVetrumai.மூன்றாம்வேற்றுமையா("தாயொடும்")==true);
		assertFalse(TamilVetrumai.நான்காம்வேற்றுமையா("பொன்னன்")==true);
		assertFalse(TamilVetrumai.ஐந்தாம்வேற்றுமையா("காட்டினி")==true);	
		assertFalse(TamilVetrumai.ஆறாம்வேற்றுமையா("எனாதும்")==true);
		assertFalse(TamilVetrumai.ஏழாம்வேற்றுமையா("வேனிற்கண்ணை")==true);
	}

}
