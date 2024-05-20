# Neural Network for MNIST Digit Recognition

This project implements a neural network for recognizing handwritten digits from the MNIST dataset using Java.

## Overview

The MNIST dataset is a collection of 28x28 pixel grayscale images of handwritten digits (0-9). The goal of this project is to train a neural network model to accurately classify these digits.

The neural network architecture consists of convolutional layers followed by max-pooling layers and fully connected layers.

## Features

- Load the MNIST dataset
- Train a neural network model
- Test the trained model on a separate test set
- Classify a single example from the test set

## Prerequisites

- Java Development Kit (JDK) 8 or higher

  ## File Structure

├── data/ # Directory for dataset files
│ ├── mnist_test.csv # Test set of MNIST dataset
│ └── mnist_train.csv # Training set of MNIST dataset
├── network/ # Package containing neural network classes
│ ├── NetworkBuilder.java # Builder class for constructing neural networks
│ └── NeuralNetwork.java # Class representing the neural network
├── data/ # Directory for dataset files
│ ├── DataReader.java # Class for reading MNIST dataset
│ └── Image.java # Class representing an image in the dataset
└── Main.java # Main class for running the program

