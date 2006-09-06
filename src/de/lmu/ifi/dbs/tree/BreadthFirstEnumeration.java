package de.lmu.ifi.dbs.tree;

import de.lmu.ifi.dbs.index.IndexPath;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Provides a breadth first enumeration over the nodes of a tree.
 *
 * @author Elke Achtert (<a
 *         href="mailto:achtert@dbs.ifi.lmu.de">achtert@dbs.ifi.lmu.de</a>)
 */
public class BreadthFirstEnumeration<E extends Enumeratable<E>> implements Enumeration<E> {

  /**
   * Represents an empty enumeration.
   */
  public final Enumeration<E> EMPTY_ENUMERATION = new Enumeration<E>() {
    public boolean hasMoreElements() {
      return false;
    }

    public E nextElement() {
      throw new NoSuchElementException("No more children");
    }
  };

  /**
   * The queue for the enumeration.
   */
  private Queue<E> queue;

  /**
   * Creates a new breadth first enumeration with the specified node as root
   * node.
   *
   * @param rootPath the root entry of the enumeration
   * @param index    the index storing the nodes
   */
  public BreadthFirstEnumeration(final E root) {
    super();
    queue = new LinkedList<E>();
    queue.offer(root);
  }

  /**
   * Tests if this enumeration contains more elements.
   *
   * @return <code>true</code> if and only if this enumeration object
   *         contains at least one more element to provide; <code>false</code>
   *         otherwise.
   */
  public boolean hasMoreElements() {
    return !queue.isEmpty();
  }

  /**
   * Returns the next element of this enumeration if this enumeration object
   * has at least one more element to provide.
   *
   * @return the next element of this enumeration.
   * @throws java.util.NoSuchElementException
   *          if no more elements exist.
   */
  public E nextElement() {
    E next = queue.remove();

    for (int i = 0; i < next.numChildren(); i++) {
      queue.offer(next.getChild(i));
    }

    return next;
  }
}
