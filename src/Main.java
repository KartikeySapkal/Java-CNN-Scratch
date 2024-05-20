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

        int epochs = 1;

        for (int i = 0; i < epochs; i++) {
            shuffle(imagesTrain);
            try (ProgressBar pb = new ProgressBar("Training Epoch " + (i + 1), imagesTrain.size())) {
                for (Image image : imagesTrain) {
                    net.trainSingle(image);
                    pb.step();
                }
            }
            rate = net.test(imagesTest);
            System.out.println("Success rate after epoch " + i + ": " + rate);


            testSingleExample(net, imagesTest.get(0));
        }


    }
    
    public static void testSingleExample(NeuralNetwork neuralNetwork, Image example) {
        System.out.println("Classifying a single example...");
        int predictedLabel = neuralNetwork.guess(example);
        System.out.println("Predicted label: " + predictedLabel);
        System.out.println("True label: " + example.getLabel());
    }

}

//Starting data loading...
//Images Train size: 60000
//Images Test size: 10000
//Pre-training success rate: 0.0739
//Training Epoch 1 100% │███████████████│ 60000/60000 (0:01:45 / 0:00:00)
//Success rate after epoch 0: 0.8616
//Classifying a single example...
//Predicted label: 7
//True label: 7



