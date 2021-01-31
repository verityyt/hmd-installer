package backend

abstract class Process {

    /**
     * Testing something before the process is going to be executed
     */
    abstract var test: Boolean

    /**
     * Current status of the process
     */
    abstract var status: Int

    abstract fun run()

}