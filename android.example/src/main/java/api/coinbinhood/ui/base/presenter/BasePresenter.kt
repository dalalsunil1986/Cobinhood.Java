package api.coinbinhood.ui.base.presenter

import api.coinbinhood.ui.base.interactor.MVPInteractor
import api.coinbinhood.ui.base.view.MVPView
import api.coinbinhood.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by joel on 3/18/18.
 */
abstract class BasePresenter<V : MVPView, I : MVPInteractor> internal constructor(protected var interactor: I?, protected val schedulerProvider: SchedulerProvider, protected val compositeDisposable: CompositeDisposable) : MVPPresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

}