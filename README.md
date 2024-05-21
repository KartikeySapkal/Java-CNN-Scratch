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

Project Structure

plaintext

├── data
│   ├── mnist_test.csv
│   └── mnist_train.csv
├── src
│   ├── data
│   │   ├── DataReader.java
│   │   └── Image.java
│   ├── layers
│   │   ├── ConvolutionLayer.java
│   │   ├── FullyConnectedLayer.java
│   │   ├── Layer.java
│   │   └── MaxPoolLayer.java
│   ├── network
│   │   ├── NetworkBuilder.java
│   │   └── NeuralNetwork.java
│   └── Main.java
└── README.md

