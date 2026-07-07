/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.sys.context;

import javax.transaction.xa.XAResource;

/**
 * Adapts a javax.transaction JTA transaction manager (e.g. Bitronix) to the
 * jakarta.transaction API expected by Spring Framework 6's
 * {@code JtaTransactionManager}.
 */
public class JakartaJtaAdapter implements jakarta.transaction.TransactionManager, jakarta.transaction.UserTransaction {

    private final javax.transaction.TransactionManager delegate;

    public JakartaJtaAdapter(javax.transaction.TransactionManager delegate) {
        this.delegate = delegate;
    }

    @Override
    public void begin() throws jakarta.transaction.NotSupportedException, jakarta.transaction.SystemException {
        try {
            delegate.begin();
        } catch (javax.transaction.NotSupportedException e) {
            throw wrap(new jakarta.transaction.NotSupportedException(e.getMessage()), e);
        } catch (javax.transaction.SystemException e) {
            throw systemException(e);
        }
    }

    @Override
    public void commit() throws jakarta.transaction.RollbackException, jakarta.transaction.HeuristicMixedException,
            jakarta.transaction.HeuristicRollbackException, SecurityException, IllegalStateException,
            jakarta.transaction.SystemException {
        try {
            delegate.commit();
        } catch (javax.transaction.RollbackException e) {
            throw wrap(new jakarta.transaction.RollbackException(e.getMessage()), e);
        } catch (javax.transaction.HeuristicMixedException e) {
            throw wrap(new jakarta.transaction.HeuristicMixedException(e.getMessage()), e);
        } catch (javax.transaction.HeuristicRollbackException e) {
            throw wrap(new jakarta.transaction.HeuristicRollbackException(e.getMessage()), e);
        } catch (javax.transaction.SystemException e) {
            throw systemException(e);
        }
    }

    @Override
    public int getStatus() throws jakarta.transaction.SystemException {
        try {
            return delegate.getStatus();
        } catch (javax.transaction.SystemException e) {
            throw systemException(e);
        }
    }

    @Override
    public jakarta.transaction.Transaction getTransaction() throws jakarta.transaction.SystemException {
        try {
            javax.transaction.Transaction tx = delegate.getTransaction();
            return tx == null ? null : new TransactionAdapter(tx);
        } catch (javax.transaction.SystemException e) {
            throw systemException(e);
        }
    }

    @Override
    public void resume(jakarta.transaction.Transaction tobj)
            throws jakarta.transaction.InvalidTransactionException, IllegalStateException, jakarta.transaction.SystemException {
        try {
            delegate.resume(tobj == null ? null : ((TransactionAdapter) tobj).delegate);
        } catch (javax.transaction.InvalidTransactionException e) {
            throw wrap(new jakarta.transaction.InvalidTransactionException(e.getMessage()), e);
        } catch (javax.transaction.SystemException e) {
            throw systemException(e);
        }
    }

    @Override
    public void rollback() throws IllegalStateException, SecurityException, jakarta.transaction.SystemException {
        try {
            delegate.rollback();
        } catch (javax.transaction.SystemException e) {
            throw systemException(e);
        }
    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, jakarta.transaction.SystemException {
        try {
            delegate.setRollbackOnly();
        } catch (javax.transaction.SystemException e) {
            throw systemException(e);
        }
    }

    @Override
    public void setTransactionTimeout(int seconds) throws jakarta.transaction.SystemException {
        try {
            delegate.setTransactionTimeout(seconds);
        } catch (javax.transaction.SystemException e) {
            throw systemException(e);
        }
    }

    @Override
    public jakarta.transaction.Transaction suspend() throws jakarta.transaction.SystemException {
        try {
            javax.transaction.Transaction tx = delegate.suspend();
            return tx == null ? null : new TransactionAdapter(tx);
        } catch (javax.transaction.SystemException e) {
            throw systemException(e);
        }
    }

    private static jakarta.transaction.SystemException systemException(javax.transaction.SystemException e) {
        jakarta.transaction.SystemException wrapped = new jakarta.transaction.SystemException(e.getMessage());
        wrapped.initCause(e);
        return wrapped;
    }

    private static <T extends Throwable> T wrap(T target, Throwable cause) {
        target.initCause(cause);
        return target;
    }

    /**
     * jakarta.transaction.Transaction view over a javax.transaction.Transaction.
     */
    public static class TransactionAdapter implements jakarta.transaction.Transaction {

        private final javax.transaction.Transaction delegate;

        public TransactionAdapter(javax.transaction.Transaction delegate) {
            this.delegate = delegate;
        }

        @Override
        public void commit() throws jakarta.transaction.RollbackException, jakarta.transaction.HeuristicMixedException,
                jakarta.transaction.HeuristicRollbackException, SecurityException, IllegalStateException,
                jakarta.transaction.SystemException {
            try {
                delegate.commit();
            } catch (javax.transaction.RollbackException e) {
                throw wrap(new jakarta.transaction.RollbackException(e.getMessage()), e);
            } catch (javax.transaction.HeuristicMixedException e) {
                throw wrap(new jakarta.transaction.HeuristicMixedException(e.getMessage()), e);
            } catch (javax.transaction.HeuristicRollbackException e) {
                throw wrap(new jakarta.transaction.HeuristicRollbackException(e.getMessage()), e);
            } catch (javax.transaction.SystemException e) {
                throw systemException(e);
            }
        }

        @Override
        public boolean delistResource(XAResource xaRes, int flag) throws IllegalStateException, jakarta.transaction.SystemException {
            try {
                return delegate.delistResource(xaRes, flag);
            } catch (javax.transaction.SystemException e) {
                throw systemException(e);
            }
        }

        @Override
        public boolean enlistResource(XAResource xaRes)
                throws jakarta.transaction.RollbackException, IllegalStateException, jakarta.transaction.SystemException {
            try {
                return delegate.enlistResource(xaRes);
            } catch (javax.transaction.RollbackException e) {
                throw wrap(new jakarta.transaction.RollbackException(e.getMessage()), e);
            } catch (javax.transaction.SystemException e) {
                throw systemException(e);
            }
        }

        @Override
        public int getStatus() throws jakarta.transaction.SystemException {
            try {
                return delegate.getStatus();
            } catch (javax.transaction.SystemException e) {
                throw systemException(e);
            }
        }

        @Override
        public void registerSynchronization(jakarta.transaction.Synchronization sync)
                throws jakarta.transaction.RollbackException, IllegalStateException, jakarta.transaction.SystemException {
            try {
                delegate.registerSynchronization(new javax.transaction.Synchronization() {
                    @Override
                    public void beforeCompletion() {
                        sync.beforeCompletion();
                    }

                    @Override
                    public void afterCompletion(int status) {
                        sync.afterCompletion(status);
                    }
                });
            } catch (javax.transaction.RollbackException e) {
                throw wrap(new jakarta.transaction.RollbackException(e.getMessage()), e);
            } catch (javax.transaction.SystemException e) {
                throw systemException(e);
            }
        }

        @Override
        public void rollback() throws IllegalStateException, jakarta.transaction.SystemException {
            try {
                delegate.rollback();
            } catch (javax.transaction.SystemException e) {
                throw systemException(e);
            }
        }

        @Override
        public void setRollbackOnly() throws IllegalStateException, jakarta.transaction.SystemException {
            try {
                delegate.setRollbackOnly();
            } catch (javax.transaction.SystemException e) {
                throw systemException(e);
            }
        }
    }
}
