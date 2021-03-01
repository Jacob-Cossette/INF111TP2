package observer;

/**
 * Classe abstraite du patron Observable.  Avise tous les obersers qui lui 
 * ont été attachés.
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018
 * @révision et commentaires Pierre Bélisle H2021
 */

import java.util.ArrayList;

public abstract class MonObservable {

	// liste des observers
	ArrayList<MonObserver> observers = new ArrayList<MonObserver>();
	
	/**
	 * Méthode pour attacher un Observer
	 * 
	 * @param observer L'observer à attacher.
	 */
	public void attacherObserver(MonObserver observer){
		observers.add(observer);
	}
	
	/**
	 * Méthode pour avertir tous les observers.
	 */
	public void avertirLesObservers(){
		for(MonObserver observer:observers){
			observer.avertir();
		}
	}
		
}
