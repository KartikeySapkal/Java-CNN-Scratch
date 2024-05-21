import data.DataReader;
import data.Image;
import network.NetworkBuilder;
import network.NeuralNetwork;
import me.tongfei.progressbar.ProgressBar;
import java.io.Serializable;
import java.util.List;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.util.Collections.shuffle;

public class Main {
    public static void main(String[] args) {
        long SEED = 123;
        System.out.println("Starting data loading...");
        List<Image> imagesTest = new DataReader().readData("data/mnist_test.csv");
        List<Image> imagesTrain = new DataReader().readData("data/mnist_train.csv");
        System.out.println("Images Train size: " + imagesTrain.size());
        System.out.println("Images Test size: " + imagesTest.size());

        NetworkBuilder builder = new NetworkBuilder(28, 28, 256 * 100);
        builder.addConvolutionLayer(8, 5, 1, 0.1, SEED);
        builder.addMaxPoolLayer(3, 2);
        builder.addFullyConnectedLayer(10, 0.1, SEED);

        NeuralNetwork net = builder.build();

        float rate = net.test(imagesTest);
        System.out.println("Pre-training success rate: " + rate);
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter No of Epochs(Recommended Epochs: 3) : ");
        int ep = scanner.nextInt();
        for (int i = 0; i < ep; i++) {
            shuffle(imagesTrain);
            try (ProgressBar pb = new ProgressBar("Training Epoch " + (i + 1), imagesTrain.size())) {
                for (Image image : imagesTrain) {
                    net.trainSingle(image);
                    pb.step();
                }
            }
            rate = net.test(imagesTest);
            System.out.println("Success rate after epoch " + i + ": " + rate);

        }
        int count = 0;
        for(int i=0; i<100; i++){
           if(testSingleExample(net, imagesTest.get(i))){
               count++;
           }
        }

        System.out.println("\n\n\n-------------Training completed: " + count + "----------------\n");
    }

    public static boolean testSingleExample(NeuralNetwork neuralNetwork, Image example) {
        System.out.println("Classifying a single example...");
        int predictedLabel = neuralNetwork.guess(example);
        System.out.println("Predicted label: " + predictedLabel);
        System.out.println("True label: " + example.getLabel());
        if(predictedLabel!=example.getLabel()){
            System.out.println("---------------\nPrediction failed\n------------------");
            return false;
        }

        return true;
    }


}


/*

Starting data loading...
Images Train size: 60000
Images Test size: 10000
Pre-training success rate: 0.0739
Training Epoch 1 100% │███████████████│ 60000/60000 (0:02:51 / 0:00:00)
Success rate after epoch 0: 0.8638
Training Epoch 2 100% │███████████████│ 60000/60000 (0:02:49 / 0:00:00)
Success rate after epoch 1: 0.8939
Training Epoch 3 100% │███████████████│ 60000/60000 (0:03:32 / 0:00:00)
Success rate after epoch 2: 0.907
Classifying a single example...
Predicted label: 7
True label: 7
Classifying a single example...
Predicted label: 2
True label: 2
Classifying a single example...
Predicted label: 1
True label: 1
Classifying a single example...
Predicted label: 0
True label: 0
Classifying a single example...
Predicted label: 4
True label: 4
Classifying a single example...
Predicted label: 1
True label: 1
Classifying a single example...
Predicted label: 4
True label: 4
Classifying a single example...
Predicted label: 9
True label: 9
    Classifying a single example...
    Predicted label: 9
    True label: 5

Classifying a single example...
Predicted label: 9
True label: 9
*/

